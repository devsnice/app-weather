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

        builder.setImageUrl(getGif(builder.getWeatherType()));

        return builder.build();
    }

    private String getGif(WeatherTypes type) {
        switch (type) {
            case BLOODY_HOT_TEMP:
                return "https://giphy.com/embed/fAv2n4Tlhshig";
            case PERFECT_TEMP:
                return "https://giphy.com/embed/3ohjUSYxOccY7zTmNO";
            case COLD_TEMP:
                return "https://giphy.com/embed/giFLHb8U7IhLgvB6wC";
            case VERY_COLD_TEMP:
                return "https://giphy.com/embed/pVQUCfmpSv1Qs";
            case STRONG_WIND:
                return "https://giphy.com/embed/nMT8FHxdwgCnS";
            case HEAVY_RAIN:
                return "https://giphy.com/embed/g0yPLFTYpr283dUJBs";
            case HEAVY_SNOW:
                return "https://giphy.com/embed/JWegbsAWQS1YA";
            default:
                throw new IllegalStateException("Weather type should be defined");
        }
    }
}
