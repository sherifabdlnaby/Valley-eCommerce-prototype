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
    private Long id;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

	@ManyToOne
	private Brand brand; //Obj suffix temp.

	@ManyToOne
	private Company company; //Obj suffix temp.

    @Column(name = "averagePrice", nullable = false, unique = false)
    private Float averagePrice;

    @Column(name = "dateTime", nullable = false, unique = false)
    private Date dateTime;

	@OneToMany(mappedBy = "product")
	private List<StoreProduct> storeProducts;

    public Product(){
        this.name = "";
        this.averagePrice = 0f;
        this.dateTime = null;
    }

    public Product(String name, Brand brand, Float averagePrice, Date dateTime) {
        this.name = name;
        this.brand = brand;
        this.averagePrice = averagePrice;
        this.dateTime = dateTime;
    }

	public void setName(String name) {
        this.name = name;
    }

    public void setBrand(Brand brand) {
        this.brand=brand;
    }

    public Long getId() {
        return id;
    }

	public void setId(Long id) {
		this.id = id;
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

    public Brand getBrand() {
        return brand;
    }

    public Float getAveragePrice() {
        return averagePrice;
    }

    public Date getDateTime() {
        return dateTime;
    }

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
