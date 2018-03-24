package com.piper.valley.models.domain;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Column(name = "brand", nullable = false, unique = false )
    private String brand;

   /* @Column(name = "company", nullable = false, unique = false)
    private Company company;*/

    @Column(name = "averagePrice", nullable = false, unique = false)
    private Float averagePrice;

    @Column(name = "dateTime", nullable = false, unique = false)
    private Date dateTime;

	@OneToMany(mappedBy = "product")
	private List<StoreProduct> storeProducts;

    public Product(){
        this.name = "";
        this.brand = "";
        this.averagePrice = 0f;
        this.dateTime = null;
    }

    public Product(String name, String brand, Float averagePrice, Date dateTime) {
        this.name = name;
        this.brand=brand;
        this.averagePrice = averagePrice;
        this.dateTime = dateTime;
    }

	public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand=brand;
    }

    public long getId() {
        return id;
    }

    public void setAveragePrice(Float averagePrice) {
        this.averagePrice = averagePrice;
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

    public Float getAveragePrice() {
        return averagePrice;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
