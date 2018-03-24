package com.piper.valley.models.domain;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class StoreOwner extends User {
	@OneToMany(mappedBy = "storeOwner")
	private List<Store> stores;
}

