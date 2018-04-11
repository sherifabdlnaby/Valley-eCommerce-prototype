package com.piper.valley.models.domain;

import org.hibernate.search.annotations.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="storeProductHistory")
public class StoreProductHistory extends StoreHistory {

    @Column(name="old_description",length = 1000)
    @Field
    private String oldProductDescription;

    public StoreProductHistory(String oldProductDescription, float oldProductPrice, String oldProductName, long storeProductID) {
        this.oldProductDescription = oldProductDescription;
        this.oldProductPrice = oldProductPrice;
        this.oldProductName = oldProductName;
        this.storeProductID = storeProductID;
    }

    private float oldProductPrice;

    @Column(name = "old_name")
    @Field
    private String oldProductName;

    @Column(name = "storeProductID", nullable = false, updatable = false)
    private long storeProductID;

    public long getStoreProductID() {
        return storeProductID;
    }

    public void setStoreProductID(long storeProductID) {
        this.storeProductID = storeProductID;
    }

    public String getOldProductDescription() {
        return oldProductDescription;
    }

    public void setOldProductDescription(String oldProductDescription) {
        this.oldProductDescription = oldProductDescription;
    }

    public float getOldProductPrice() {
        return oldProductPrice;
    }

    public void setOldProductPrice(float oldProductPrice) {
        this.oldProductPrice = oldProductPrice;
    }

    public String getOldProductName() {
        return oldProductName;
    }

    public void setOldProductName(String oldProductName) {
        this.oldProductName = oldProductName;
    }


}
