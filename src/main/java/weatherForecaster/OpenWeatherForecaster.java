package weatherForecaster;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.City;
import model.Country;
import model.Forecast;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static model.Forecast.Builder.forecast;

public class OpenWeatherForecaster implements WeatherForecaster {
    private String API_KEY;

    public OpenWeatherForecaster(String apiKey) {
        this.API_KEY = apiKey;
    }

    @Override
    public Forecast getByCountryAndCity(Country country, City city) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NEVER)
            .connectTimeout(Duration.ofSeconds(5))
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(createApiURI(country, city))
            .header("Content-Type", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == HttpStatus.OK_200) {
            return parseResponse(response.body());
        } else {
            throw new RuntimeException(String.format("Problem with getting forecast because open weather returned %s", response.statusCode()));
        }
    }

    private URI createApiURI(Country country, City city) throws UnsupportedEncodingException {
        return URI.create(String.format(
            "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s&units=metric",
            URLEncoder.encode(city.toString(), UTF_8.toString()),
            URLEncoder.encode(country.toString(), UTF_8.toString()),
            API_KEY
        ));
    }

    private Forecast parseResponse(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        final JsonNode jsonNode = mapper.readTree(response);

        Optional<Integer> clouds = Optional.ofNullable(jsonNode.get("clouds")).map(c -> c.get("all").asInt());
        Optional<Double> rainForLastHour = Optional.ofNullable(jsonNode.get("rain")).map(r -> r.get("1h").asDouble());
        Optional<Double> snowForLastHour = Optional.ofNullable(jsonNode.get("snow")).map(s -> s.get("all").asDouble());
        Optional<Double> windSpeed = Optional.ofNullable(jsonNode.get("wind")).map(c -> c.get("speed").asDouble());

        return forecast()
            .setFeelsLike(jsonNode.get("main").get("feels_like").asDouble())
            .setClouds(clouds)
            .setRainForLastHour(rainForLastHour)
            .setSnowForLastHour(snowForLastHour)
            .setWindSpeed(windSpeed)
            .build();
    }
}
