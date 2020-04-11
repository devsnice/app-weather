package app;

import weatherForecaster.OpenWeatherForecaster;

public class App {
    public static void main(String[] args) {
        new AppClient(new OpenWeatherForecaster()).initClient();
    }
}