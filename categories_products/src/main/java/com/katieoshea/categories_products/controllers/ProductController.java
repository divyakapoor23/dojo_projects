package com.katieoshea.categories_products.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katieoshea.categories_products.models.Category;
import com.katieoshea.categories_products.models.Product;
import com.katieoshea.categories_products.services.CategoryServ;
import com.katieoshea.categories_products.services.ProductServ;


@Controller
public class ProductController {
    private ProductServ pS;
    private CategoryServ cS;
    public ProductController(ProductServ pS, CategoryServ cS) {
        this.pS = pS;
        this.cS = cS;
    }

    @RequestMapping("/products/new")
    public String products(@ModelAttribute("newProduct") Product product) {
        return "newProd";
    }
    @PostMapping("/products/new")
    public String create(@Valid @ModelAttribute("newProduct") Product product, BindingResult result) {
        if(result.hasErrors()) {
        	System.out.println(result.getAllErrors());
            return "newProd";
        }
        pS.create(product);
        return "redirect:/products/new";
    }

    @RequestMapping("/products/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
    	Product product = pS.findProduct(id);
		List<Category> allCategs = cS.all();
		List<Category> notCats = new ArrayList<Category>();
		List<Category> prodCats = product.getCategories();
        if(prodCats.size() == 0) {
        	model.addAttribute("none", "No categories added yet");
        }
        for(Category x: allCategs) {
        	int count = 0;
        	for(Category y: prodCats) {
        		if(y != x) {
        			count++;
        			if(count == prodCats.size()) {
        				notCats.add(x);
        			}
    			}
    		}
        }
        model.addAttribute("availCats", notCats);
        model.addAttribute("product", product);
        return("showProd");
    }
    @PostMapping("/products/{product_id}/join")
    public String join(@RequestParam("category_id") Long category_id, @PathVariable("product_id") Long product_id) {
        Product product = pS.findProduct(product_id);
        Category category = cS.findCategory(category_id);        
        List<Category> categories = product.getCategories();
        categories.add(category);
        product.setCategories(categories);
        pS.update(product);
        return "redirect:/products/"+product_id;
    }
}
