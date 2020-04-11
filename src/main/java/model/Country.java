package model;

public class Country extends Microtype<String> {
   public Country(String value) {
       super(value);
   }

    public static Country from(String countryName) {
        return new Country(countryName);
    }

    @Override
    public String toString() {
        return value;
    }
}
