package model;

public class WeatherAnalyzerResult {
    public final String description;
    public final String imageUrl;
    public final WeatherTypes weatherType;

    public WeatherAnalyzerResult(Builder builder) {
        this.description = builder.description;
        this.imageUrl = builder.imageUrl;
        this.weatherType = builder.weatherType;
    }

    @Override
    public String toString() {
        return "WeatherAnalyzerResult{" +
                "description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", weatherType=" + weatherType +
                '}';
    }

    public static class Builder {
        private String description;
        private String imageUrl;
        private WeatherTypes weatherType;

        public static Builder weatherAnalyzerResult() {
            return new Builder();
        }

        public Builder setDescription(String description) {
            this.description = description;

            return this;
        }

        public Builder enhanceDescription(String addition) {
            this.description = this.description == null ? addition : this.description.concat(", ").concat(addition);

            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;

            return this;
        }

        public Builder setWeatherType(WeatherTypes weatherType) {
            this.weatherType = weatherType;

            return this;
        }

        public WeatherTypes getWeatherType() {
            return this.weatherType;
        }

        public WeatherAnalyzerResult build() {
            return new WeatherAnalyzerResult(this);
        }
    }
}
