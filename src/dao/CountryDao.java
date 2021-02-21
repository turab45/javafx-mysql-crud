package dao;

import models.Country;

import java.util.List;

public interface CountryDao {
    public Integer addCountry(Country country);
    public Integer updateCountry(Country country);
    public Integer deleteCountry(int countryId);
    public Country getCountryById(int countryId);
    public Integer getCountrytIdByName(String country);
    public List<Country> getAllCountry();
}
