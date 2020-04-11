package app;

import static spark.Spark.*;

public class AppClient {
    public void initClient() {
        port(8080);
        staticFiles.location("/public");

        get("/hello", (req, res) -> {
           return "hello";
        });
    }
}
