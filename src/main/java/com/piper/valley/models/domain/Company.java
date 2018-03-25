package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Integer id;

    private String name;
    @OneToMany(mappedBy = "company")
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
