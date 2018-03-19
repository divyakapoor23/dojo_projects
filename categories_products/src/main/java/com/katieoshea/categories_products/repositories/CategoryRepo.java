package com.katieoshea.categories_products.repositories;

import com.katieoshea.categories_products.models.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {

}
    
