package com.katieoshea.categories_products.models;

import com.katieoshea.categories_products.models.Product;

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
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private Date updatedAt;
    
    @Column(updatable=false)
    private Date createdAt;
    
    @ManyToMany(fetch=FetchType.LAZY) //one category can belong to many products
    @JoinTable(
        name="categories_products", //must be same name!! this is the many to many table in MySQL
        joinColumns=@JoinColumn(name="category_id"), //current class
        inverseJoinColumns=@JoinColumn(name="product_id") //other class
    )
    private List<Product> products;

    public Category() {}
    public Category(String name) {
        this.name = name;
    }

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

    @PrePersist
	public void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
	
	public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }
}
