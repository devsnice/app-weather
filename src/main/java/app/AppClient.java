package app;

import static spark.Spark.*;

public class AppClient {
    public void initClient() {
        staticFiles.location("/public");
        port(8080);

        get("/weatherForecast", (req, res) -> {
            final String country = req.queryParams("country");
            final String city = req.queryParams("city");

            return "weather";
        });
    }
}
