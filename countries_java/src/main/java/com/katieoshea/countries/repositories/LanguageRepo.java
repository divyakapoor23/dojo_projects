package com.katieoshea.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.katieoshea.countries.models.Language;

public interface LanguageRepo extends CrudRepository<Language, Long> {

    // 4. What query would you run to get all languages in each country with a percentage greater than 89%? Your query should arrange the result by percentage in descending order.
    @Query(value="SELECT countries.name, languages.language, languages.percentage FROM languages JOIN countries ON countries.id = languages.country_id WHERE languages.percentage > 89 ORDER BY languages.percentage DESC;", nativeQuery=true)
    List<Object[]> findAllLangsSpokenOver89();
}
