package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.Date;
@Entity     // This tells Hibernate to make a table out of this class
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Column(name = "brand", nullable = false, unique = false )
    private Brand brand;

   /* @Column(name = "company", nullable = false, unique = false)
    private Company company;*/

    @Column(name = "price", nullable = false, unique = false)
    private float price;

    @Column(name = "dateTime", nullable = false, unique = false)
    private Date dateTime;

    public Product(){
        this.name = "";
        this.brand = null;
        this.price = 0;
        this.dateTime = null;
    }
    public Product(String name, Brand brand, float price, Date dateTime) {
        this.name = name;
        this.brand=brand;
        this.price = price;
        this.dateTime = dateTime;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(Brand brand) {
        this.brand=brand;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public float getPrice() {
        return price;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
