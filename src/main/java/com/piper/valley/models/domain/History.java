package com.piper.valley.models.domain;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@Inheritance( strategy = InheritanceType.JOINED )
public class History {

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
    private HistoryType type;


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

    public HistoryType getType() {
        return type;
    }

    public void setType(HistoryType type) {
        this.type = type;
    }

}
