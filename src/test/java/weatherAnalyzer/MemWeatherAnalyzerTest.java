package weatherAnalyzer;

import model.Forecast;
import model.WeatherAnalyzerResult;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static model.Forecast.Builder.forecast;
import static org.assertj.core.api.Assertions.assertThat;

class MemWeatherAnalyzerTest {
    final MemWeatherAnalyzer analyzer = new MemWeatherAnalyzer();

    @Test
    public void should_anylyze_temperature_correct() {
        var forecastForToday = baseForecast()
                .setFeelsLike(10.5)
                .build();

        WeatherAnalyzerResult analysis = analyzer.analyze(forecastForToday);

        assertThat(analysis.description).isEqualTo("It feels like 10.5, it's 2020 year, stay home!");
    }

    @Test
    public void should_anylyze_correct__when_rain_is_really_heavy() {
        var forecastForToday = baseForecast()
                .setRainForLastHour(Optional.of(5.4))
                .build();

        WeatherAnalyzerResult analysis = analyzer.analyze(forecastForToday);

        assertThat(analysis.description).isEqualTo("It feels like 0.0, rain volume for last 1 hour – 5.4 mm, it's 2020 year, stay home!");
    }

    @Test
    public void should_anylyze_correct__when_snow_is_really_heavy() {
        var forecastForToday = baseForecast()
                .setSnowForLastHour(Optional.of(6.1))
                .build();

        WeatherAnalyzerResult analysis = analyzer.analyze(forecastForToday);

        assertThat(analysis.description).isEqualTo("It feels like 0.0, snow volume for last 1 hour – 6.1 mm, it's 2020 year, stay home!");
    }


    private Forecast.Builder baseForecast() {
        return forecast()
                .setFeelsLike(0.0)
                .setClouds(empty())
                .setRainForLastHour(empty())
                .setSnowForLastHour(empty())
                .setWindSpeed(empty());
    }

}