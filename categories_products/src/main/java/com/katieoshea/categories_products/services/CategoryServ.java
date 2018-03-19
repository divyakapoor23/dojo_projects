package com.katieoshea.categories_products.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.katieoshea.categories_products.models.Category;
import com.katieoshea.categories_products.repositories.CategoryRepo;

@Service
public class CategoryServ {
    private CategoryRepo categoryRepo;

    public CategoryServ(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    public void create(Category categ) {
        categoryRepo.save(categ);
    }
    public ArrayList<Category> all() {
        return (ArrayList<Category>) categoryRepo.findAll();
    }
    public Category findCategory(Long id) {
        return categoryRepo.findOne(id);
    }
    // public void destroy(Long id) {
    //     categoryRepo.deleteById(id);
    // }
    public void destroy(Category categ) {
        categoryRepo.delete(categ);
    }
    public void update(Category categ) {
        categoryRepo.save(categ);
    }
}
