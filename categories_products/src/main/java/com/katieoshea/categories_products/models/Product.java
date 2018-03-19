package com.katieoshea.categories_products.models;

import com.katieoshea.categories_products.models.Category;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String description;
    private double price;
    private Date updatedAt;

    @Column(updatable=false)
    private Date createdAt;

    @ManyToMany(fetch=FetchType.LAZY) //one product can belong to many categories
    @JoinTable(
        name="categories_products", //must be same name!! this is the many to many table in MySQL
        joinColumns=@JoinColumn(name="product_id"), //current class
        inverseJoinColumns=@JoinColumn(name="category_id") //other class
    )
    private List<Category> categories;
    

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

    @PrePersist
	public void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
	
	public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public List<Category> getCategories() {
        return categories;
    }
    
    public Product() {}
    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
