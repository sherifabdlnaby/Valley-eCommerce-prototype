package com.piper.valley.models.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="storeCollabHistory")
public class StoreCollabHistory extends StoreHistory {
    @ManyToOne
    private User collab;

	public StoreCollabHistory(User user, Store store, String message, Date dateTime, StoreHistoryType type, User collab) {
		super(user, store, message, dateTime, type);
		this.collab = collab;
	}

	public User getCollab() {
		return collab;
	}

	public void setCollab(User collab) {
		this.collab = collab;
	}

	public StoreCollabHistory() {
	}
}
