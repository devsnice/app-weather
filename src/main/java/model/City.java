package model;

public class City extends Microtype<String> {
   public City(String value) {
       super(value);
   }

   public static City from(String cityName) {
       return new City(cityName);
   }

    @Override
    public String toString() {
        return value;
    }
}
