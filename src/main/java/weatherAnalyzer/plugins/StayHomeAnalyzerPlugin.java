package weatherAnalyzer.plugins;

import model.Forecast;
import model.WeatherAnalyzerResult;

import java.util.Calendar;

public class StayHomeAnalyzerPlugin extends WeatherAnalyzerPlugin {
    @Override
    public WeatherAnalyzerResult.Builder run(WeatherAnalyzerResult.Builder analysis, Forecast forecast) {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (year == 2020) {
            analysis.enhanceDescription("it's 2020 year, stay home!");
        }

        return this.runNext(analysis, forecast);
    }
}
