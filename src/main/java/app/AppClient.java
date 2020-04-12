package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.City;
import model.Country;
import model.Forecast;
import model.WeatherAnalyzerResult;
import weatherAnalyzer.WeatherAnalyzer;
import weatherForecaster.WeatherForecaster;

import static spark.Spark.*;

public class AppClient {
    WeatherForecaster weatherForecaster;
    WeatherAnalyzer weatherAnalyzer;

    public AppClient(WeatherForecaster weatherForecaster, WeatherAnalyzer weatherAnalyzer) {
        this.weatherForecaster = weatherForecaster;
        this.weatherAnalyzer = weatherAnalyzer;
    }

    public void initClient() {
        staticFiles.location("/public");
        port(8080);

        get("/weatherForecast", (req, res) -> {
            // TODO: add validation
            final Country country = Country.from(req.queryParams("country"));
            final City city = City.from(req.queryParams("city"));

            Forecast forecast = weatherForecaster.getByCountryAndCity(country, city);
            WeatherAnalyzerResult analysis = weatherAnalyzer.analyze(forecast);

            return new ObjectMapper().writeValueAsString(analysis);
        });
    }
}
