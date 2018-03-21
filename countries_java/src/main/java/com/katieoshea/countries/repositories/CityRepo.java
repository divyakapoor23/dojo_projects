package com.katieoshea.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.katieoshea.countries.models.City;

public interface CityRepo extends CrudRepository<City, Long>{

    // 3. What query would you run to get all the cities in Mexico with a population of greater than 500,000? Your query should arrange the result by population in descending order. 
    @Query(value="SELECT cities.name, cities.population FROM cities JOIN countries WHERE countries.name = 'Mexico' AND cities.population > 500000;", nativeQuery=true)
    List<Object[]> findCitiesMexicoPopulation500k();

    // 7. What query would you run to get all the cities of Argentina inside the Buenos Aires district and have the population greater than 500, 000? The query should return the Country Name, City Name, District, and Population.
    @Query(value="SELECT countries.name AS Country_Name, cities.name AS City_Name, cities.district, cities.population FROM countries JOIN cities ON countries.id = cities.country_id WHERE cities.district = 'Buenos Aires' AND cities.population > 500000;", nativeQuery=true)
    List<Object[]> findCitiesArgentinaPop500k();
}
