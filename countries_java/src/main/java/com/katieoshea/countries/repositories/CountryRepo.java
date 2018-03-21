package com.katieoshea.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.katieoshea.countries.models.Country;

public interface CountryRepo extends CrudRepository<Country, Long>{

    // 1. What query would you run to get all the countries that speak Slovene? Your query should return the name of the country, language and language percentage. Your query should arrange the result by language percentage in descending order.
    @Query(value="SELECT countries.name, languages.language, languages.percentage FROM countries JOIN languages ON countries.id = languages.country_id WHERE language = 'Slovene'", nativeQuery=true)
    List<Object[]> joinAllCountriesSlovene();

    // 2. What query would you run to display the total number of cities for each country? Your query should return the name of the country and the total number of cities. Your query should arrange the result by the number of cities in descending order.
    @Query(value="SELECT countries.name, COUNT(cities.id) AS num_cities FROM countries JOIN cities ON countries.id = cities.country_id GROUP BY countries.id ORDER BY num_cities DESC;", nativeQuery=true)
    List<Object[]> findAllCountriesTotalCities();

    // 5. What query would you run to get all the countries with Surface Area below 501 and Population greater than 100,000?
    @Query(value="SELECT name, surface_area, population FROM countries WHERE countries.surface_area<501 AND countries.population>100000;", nativeQuery=true)
    List<Object[]> findCountriesBySize();

    // 6. What query would you run to get countries with only Constitutional Monarchy with a surface area of more than 200 and a life expectancy greater than 75 years?
    @Query(value="SELECT name FROM countries WHERE government_form = 'Constitutional Monarchy' AND surface_area > 200 AND life_expectancy > 75;", nativeQuery=true)
    List<Object[]> findCountriesConstMonarchySA200LE75();
    
    // 8. What query would you run to summarize the number of countries in each region? The query should display the name of the region and the number of countries. Also, the query should arrange the result by the number of countries in descending order.
    @Query(value="SELECT countries.region, COUNT(countries.id) AS num_countries FROM countries GROUP BY countries.region ORDER BY num_countries DESC;", nativeQuery=true)
    List<Object[]> findCountriesRegions();
}
