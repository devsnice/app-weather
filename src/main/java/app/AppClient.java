package app;

import model.City;
import model.Country;
import weatherForecaster.WeatherForecaster;

import static spark.Spark.*;

public class AppClient {
    WeatherForecaster weatherForecaster;

    public AppClient(WeatherForecaster weatherForecaster) {
        this.weatherForecaster = weatherForecaster;
    }

    public void initClient() {
        staticFiles.location("/public");
        port(8080);

        get("/weatherForecast", (req, res) -> {
            final Country country = Country.from(req.queryParams("country"));
            final City city = City.from(req.queryParams("city"));

            return weatherForecaster.getByCountryAndCity(country, city);
        });
    }
}
