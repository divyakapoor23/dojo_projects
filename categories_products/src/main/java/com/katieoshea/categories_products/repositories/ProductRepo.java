package com.katieoshea.categories_products.repositories;

import com.katieoshea.categories_products.models.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

}
