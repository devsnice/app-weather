package model;

import java.util.Objects;
import java.util.Optional;

public class Forecast {
    private Double feelsLike;
    private Optional<Double> windSpeed;
    private Optional<Integer> clouds;
    private Optional<Double> rainForLastHour;
    private Optional<Double> snowForLastHour;


    public Forecast(Builder builder) {
        this.feelsLike = builder.feelsLike;
        this.windSpeed = builder.windSpeed;
        this.clouds = builder.clouds;
        this.rainForLastHour = builder.rainForLastHour;
        this.snowForLastHour = builder.snowForLastHour;
    }


    @Override
    public String toString() {
        return "Forecast{" +
            "feelsLike=" + feelsLike +
            ", windSpeed=" + windSpeed +
            ", clouds=" + clouds +
            ", rainForLastHour=" + rainForLastHour +
            ", snowForLastHour=" + snowForLastHour +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(feelsLike, forecast.feelsLike) &&
            Objects.equals(windSpeed, forecast.windSpeed) &&
            Objects.equals(clouds, forecast.clouds) &&
            Objects.equals(rainForLastHour, forecast.rainForLastHour) &&
            Objects.equals(snowForLastHour, forecast.snowForLastHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feelsLike, windSpeed, clouds, rainForLastHour, snowForLastHour);
    }

    public static class Builder {
        private Double feelsLike;
        private Optional<Double> windSpeed;
        private Optional<Integer> clouds;
        private Optional<Double> rainForLastHour;
        private Optional<Double> snowForLastHour;

        public static Builder forecast() {
            return new Builder();
        }

        public Builder setFeelsLike(Double feelsLike) {
            this.feelsLike = feelsLike;

            return this;
        }

        public Builder setWindSpeed(Optional<Double> windSpeed) {
            this.windSpeed = windSpeed;

            return this;
        }

        public Builder setClouds(Optional<Integer> clouds) {
            this.clouds = clouds;

            return this;
        }

        public Builder setRainForLastHour(Optional<Double> rainForLastHour) {
            this.rainForLastHour = rainForLastHour;

            return this;
        }

        public Builder setSnowForLastHour(Optional<Double> snowForLastHour) {
            this.snowForLastHour = snowForLastHour;

            return this;
        }

        public Forecast build() {
            return new Forecast(this);
        }
    }
}
