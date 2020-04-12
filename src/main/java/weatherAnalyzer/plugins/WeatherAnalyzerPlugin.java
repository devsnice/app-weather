package weatherAnalyzer.plugins;

import model.Forecast;
import model.WeatherAnalyzerResult;

public abstract class WeatherAnalyzerPlugin {
    private WeatherAnalyzerPlugin next;

    public WeatherAnalyzerPlugin next(WeatherAnalyzerPlugin nextPlugin) {
        this.next = nextPlugin;

        return next;
    }

    public WeatherAnalyzerResult.Builder runNext(WeatherAnalyzerResult.Builder analysis, Forecast forecast) {
        if (this.next == null) {
            return analysis;
        }

        return this.next.run(analysis, forecast);
    }

    abstract public WeatherAnalyzerResult.Builder run(WeatherAnalyzerResult.Builder analysis, Forecast forecast);
}
