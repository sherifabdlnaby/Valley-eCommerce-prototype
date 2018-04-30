package com.piper.valley.models.domain;

import com.piper.valley.models.enums.StoreHistoryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="storeProductHistory")
public class StoreProductHistory extends StoreHistory {
    private Long storeId;

    private Long productId;

    @Column(name="old_description",length = 1000)
    private String oldProductDescription;

    private float oldProductPrice;

    @Column(name = "old_name")
    private String oldProductName;

    @Column(name = "storeProductID", nullable = false, updatable = false)
    private long storeProductID;

    public StoreProductHistory(User user, Store store, String message, Date dateTime, StoreHistoryType type, Long storeId, Long productId, String oldProductDescription, float oldProductPrice, String oldProductName, long storeProductID) {
        super(user, store, message, dateTime, type);
        this.storeId = storeId;
        this.productId = productId;
        this.oldProductDescription = oldProductDescription;
        this.oldProductPrice = oldProductPrice;
        this.oldProductName = oldProductName;
        this.storeProductID = storeProductID;
    }

    public StoreProductHistory()
    {

    }

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


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
