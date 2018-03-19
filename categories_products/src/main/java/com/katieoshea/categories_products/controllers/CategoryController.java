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

import com.katieoshea.categories_products.services.CategoryServ;
import com.katieoshea.categories_products.models.Category;
import com.katieoshea.categories_products.services.ProductServ;
import com.katieoshea.categories_products.models.Product;



@Controller
public class CategoryController {
    private CategoryServ cS;
    private ProductServ pS;
    public CategoryController(CategoryServ cS, ProductServ pS) {
        this.cS = cS;
        this.pS = pS;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", cS.all()); //cS.all() automatically returns an ArrayList, you could also create a variable instead on a second line:: ArrayList<Category> categories = cS.all(); model.addAttribute("categories", categories);
        model.addAttribute("products", pS.all());
        return "index"; 
    }

    @RequestMapping("/categories/new")
    public String categories(Model model) {
        model.addAttribute("newCategory", new Category()); 
        return "newCateg";
    }
    @PostMapping("/categories/new")
    public String create(@Valid @ModelAttribute("newCategory") Category cat, BindingResult result) {
        if(result.hasErrors()) {
            return "newCateg";
        }
        cS.create(cat);
        return "redirect:/categories/new";
    }

    @RequestMapping("/categories/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model) {
        Category categ = cS.findCategory(id);
        List<Product> products = categ.getProducts();
        List<Product> allProds = pS.all();
		List<Product> notProds = new ArrayList<Product>();
        if(products.size() == 0) {
            model.addAttribute("none", "No products added yet");
        }
        for(Product x: allProds) {
        	int count = 0;
        	for(Product y: products) {
        		if(y != x) {
        			count++;
        			if(count == products.size()) {
        				notProds.add(x);
        			}
    			}
    		}
        }
        model.addAttribute("category", categ);
        model.addAttribute("availProds", notProds);
        return("showCateg");
    }
    @PostMapping("/categories/{category_id}/join")
    public String join(@RequestParam("product_id") Long product_id, @PathVariable("category_id") Long category_id) {
        Category category = cS.findCategory(category_id);
        Product product = pS.findProduct(product_id);
        List<Product> products = category.getProducts();
        products.add(product);
        category.setProducts(products);
        cS.update(category);
        return "redirect:/categories/"+category_id;
    }
}
