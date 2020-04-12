package weatherAnalyzer;

import model.Forecast;
import model.WeatherAnalyzerResult;
import model.WeatherTypes;
import weatherAnalyzer.plugins.*;

public class MemWeatherAnalyzer implements WeatherAnalyzer {
    WeatherAnalyzerPlugin weatherAnalyzer;

    public MemWeatherAnalyzer() {
        WeatherAnalyzerPlugin tempAnalyzer = new TempartureAnalyzerPlugin();
        WeatherAnalyzerPlugin rainAnalyzer = new RainAnalyzerPlugin();
        WeatherAnalyzerPlugin snowAnalyzer = new SnowAnalyzerPlugin();
        WeatherAnalyzerPlugin stayHomeAnalyzer = new StayHomeAnalyzerPlugin();

        tempAnalyzer.next(rainAnalyzer);
        rainAnalyzer.next(snowAnalyzer);
        snowAnalyzer.next(stayHomeAnalyzer);

        this.weatherAnalyzer = tempAnalyzer;
    }

    @Override
    public WeatherAnalyzerResult analyze(Forecast forecast) {
        final WeatherAnalyzerResult.Builder builder =
                weatherAnalyzer.run(WeatherAnalyzerResult.Builder.weatherAnalyzerResult(), forecast);

        builder.setImageUrl(lookForMem(builder.getWeatherType()));

        return builder.build();
    }

    private String lookForMem(WeatherTypes type) {
        return type.toString();
//        switch (type) {
//            default:
//                return "";
//        }
    }
}
