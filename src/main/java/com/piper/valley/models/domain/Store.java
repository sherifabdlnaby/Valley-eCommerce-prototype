package com.piper.valley.models.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

//@Entity // This tells Hibernate to make a table out of this class
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String name;
    private User owner;
    private ArrayList<Product> products;
    public Store() {
        this.id="";
        this.name="";
        this.owner= null;
        products=null;
    }

    public Store(String id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        products= new ArrayList<>();
    }

    public Store(String id, String name, User owner, ArrayList<Product> products) {
        this.id = id;
        this.name = name;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
