package weatherAnalyzer.plugins;

import model.Forecast;
import model.WeatherAnalyzerResult;
import model.WeatherTypes;

public class RainAnalyzerPlugin extends WeatherAnalyzerPlugin {
    @Override
    public WeatherAnalyzerResult.Builder run(WeatherAnalyzerResult.Builder analysis, Forecast forecast) {
        if (forecast.rainForLastHour.isPresent()) {
            final Double rainForLastHour = forecast.rainForLastHour.get();

            analysis.enhanceDescription(String.format("rain volume for last 1 hour – %s mm", rainForLastHour));

            if (rainForLastHour > 1) {
                analysis.setWeatherType(WeatherTypes.HEAVY_RAIN);
            }
        }

        return this.runNext(analysis, forecast);
    }
}
