package weatherAnalyzer;

import model.Forecast;
import model.WeatherAnalyzerResult;

public interface WeatherAnalyzer {
    public WeatherAnalyzerResult analyze(Forecast forecast);
}
