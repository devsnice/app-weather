package weatherAnalyzer.plugins;

import model.Forecast;
import model.WeatherAnalyzerResult;
import model.WeatherTypes;

public class SnowAnalyzerPlugin extends WeatherAnalyzerPlugin {
    @Override
    public WeatherAnalyzerResult.Builder run(WeatherAnalyzerResult.Builder analysis, Forecast forecast) {
        if (forecast.snowForLastHour.isPresent()) {
            final Double snowForLastHour = forecast.snowForLastHour.get();

            analysis.enhanceDescription(String.format("snow volume for last 1 hour – %s mm", snowForLastHour));

            if (snowForLastHour > 1) {
                analysis.setWeatherType(WeatherTypes.HEAVY_SNOW);
            }
        }

        return this.runNext(analysis, forecast);
    }
}
