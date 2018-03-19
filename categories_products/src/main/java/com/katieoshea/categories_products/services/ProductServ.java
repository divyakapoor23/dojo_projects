package com.katieoshea.categories_products.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.katieoshea.categories_products.models.Product;
import com.katieoshea.categories_products.repositories.ProductRepo;

@Service
public class ProductServ {
    private ProductRepo productRepo;
    public ProductServ(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    
    public void create(Product prod) {
        productRepo.save(prod);
    }
    public ArrayList<Product> all() {
        return (ArrayList<Product>) productRepo.findAll();
    }
    public Product findProduct(Long id) {
        return productRepo.findOne(id);
    }
    // public void destroy(Long id) {
    //     ProductRepo.deleteById(id);
    // }
    public void destroy(Product prod) {
        productRepo.delete(prod);
    }
    public void update(Product prod) {
         productRepo.save(prod);
    }
}
