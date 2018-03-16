package com.katieoshea.dojosninjas.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="ninjas")
public class Ninja {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Size(min=2, max=64)
    private String first_name;

    @Column
    @Size(min=2, max=64)
    private String last_name;
    
    @Column
    private int age;

    @Column(updatable=false)
    private Date createdAt;
	
	@Column
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dojo_id")
    private Dojo dojo;
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	
	public Dojo getDojo() {
		return dojo;
	}
	public void setDojo(Dojo dojo) {
		this.dojo = dojo;
	}

    
    public Ninja() {}
    public Ninja(String first_name, String last_name, int age) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.age = age;
    }
    
}