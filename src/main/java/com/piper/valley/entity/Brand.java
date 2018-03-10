package com.piper.valley.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity // This tells Hibernate to make a table out of this class
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    @Column(unique = true) //Unique username.
    private String name;
    private ArrayList<Product> products;

    public Brand() {
        id="";
        name="";
        products=null;
    }

    public Brand(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public Brand(String id, String name, ArrayList<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
