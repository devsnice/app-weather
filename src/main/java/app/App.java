package app;

import weatherAnalyzer.MemWeatherAnalyzer;
import weatherForecaster.OpenWeatherForecaster;

public class App {
    public static void main(String[] args) {
        new AppClient(
                new OpenWeatherForecaster(),
                new MemWeatherAnalyzer()
        ).initClient();
    }
}