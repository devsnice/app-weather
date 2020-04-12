package weatherAnalyzer.plugins;

import model.Forecast;
import model.WeatherAnalyzerResult;
import model.WeatherTypes;

public class TempartureAnalyzerPlugin extends WeatherAnalyzerPlugin {
    @Override
    public WeatherAnalyzerResult.Builder run(WeatherAnalyzerResult.Builder analysis, Forecast forecast) {
        analysis.enhanceDescription(String.format("It feels like %s °C", forecast.feelsLike));

        if (forecast.feelsLike > 30) {
            analysis.setWeatherType(WeatherTypes.BLOODY_HOT_TEMP);
        } else if (forecast.feelsLike > 12.5) {
            analysis.setWeatherType(WeatherTypes.PERFECT_TEMP);
        } else if (forecast.feelsLike > -5) {
            analysis.setWeatherType(WeatherTypes.COLD_TEMP);
        } else if (forecast.feelsLike < -5) {
            analysis.setWeatherType(WeatherTypes.VERY_COLD_TEMP);
        }

        return this.runNext(analysis, forecast);
    }
}
