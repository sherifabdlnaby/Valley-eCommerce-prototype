package com.piper.valley.models.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "StoreHistory")
@Inheritance( strategy = InheritanceType.JOINED )
public class StoreHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "message", nullable = false, updatable = true)
    @Field
    private String message;

    @Column(name = "dateTime", nullable = false, unique = false)
    private Date dateTime;

    @Column(name = "type", nullable = false, unique = false)
    private StoreHistoryType type;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @ManyToOne
    @IndexedEmbedded
    private Store store;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public StoreHistoryType getType() {
        return type;
    }

    public void setType(StoreHistoryType type) {
        this.type = type;
    }

}
