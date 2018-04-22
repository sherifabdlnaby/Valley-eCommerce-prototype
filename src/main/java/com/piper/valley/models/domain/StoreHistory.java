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

    @ManyToOne
    @IndexedEmbedded
    private User user;


    @ManyToOne
    @IndexedEmbedded
    private Store store;


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

    public StoreHistory() {

    }

    public StoreHistory(User user, Store store, String message, Date dateTime, StoreHistoryType type) {
        this.user = user;
        this.store = store;
        this.message = message;
        this.dateTime = dateTime;
        this.type = type;
    }

    public void setStore(Store store) {
        this.store = store;
    }

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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
