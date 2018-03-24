package com.piper.valley.models.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.ArrayList;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class PhysicalStore extends Store {
	private String address;
}

