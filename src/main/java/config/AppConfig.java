package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class AppConfig {
    public String apiKey;

    public AppConfig(String apiKey) {
        this.apiKey = apiKey;
    }

    public static AppConfig initConfig(File file) throws IOException {
        final JsonNode jsonNode = new ObjectMapper().readTree(file);

        return new AppConfig(jsonNode.get("api-key").asText());
    }
}
