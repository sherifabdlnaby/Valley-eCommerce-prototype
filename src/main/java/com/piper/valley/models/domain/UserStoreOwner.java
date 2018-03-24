package com.piper.valley.models.domain;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class UserStoreOwner extends User {
	
}

