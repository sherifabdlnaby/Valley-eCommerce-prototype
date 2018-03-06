package com.piper.valley.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity // This tells Hibernate to make a table out of this class
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String name;
    private String brand;
    private Company company;
    private float price;
    private Date dateTime;
    private Boolean accepted ;
    public Product(){
        this.name = "";
        this.brand = "";
        this.price = 0;
        this.dateTime = null;
		this.accepted= false;
    }
    public Product(String name, String brand, float price, Date dateTime,Boolean accepted,Company company) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.dateTime = dateTime;
        this.accepted= accepted;
        this.company= company;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getBrand() {
        return brand;
    }

    public float getPrice() {
        return price;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
