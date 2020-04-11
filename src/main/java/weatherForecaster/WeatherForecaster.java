package weatherForecaster;

import model.City;
import model.Country;
import model.Forecast;

import java.io.IOException;

public interface WeatherForecaster {
    Forecast getByCountryAndCity(Country country, City city) throws IOException, InterruptedException;
}