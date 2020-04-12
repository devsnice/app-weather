package app;

import config.AppConfig;
import weatherAnalyzer.MemWeatherAnalyzer;
import weatherForecaster.OpenWeatherForecaster;

import java.io.File;
import java.io.IOException;

import static config.AppConfig.initConfig;

public class App {
    public static void main(String[] args) throws IOException {
        AppConfig config = initConfig(new File("./config.json"));

        new AppClient(
                new OpenWeatherForecaster(config.apiKey),
                new MemWeatherAnalyzer()
        ).initClient();
    }
}