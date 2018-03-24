package com.piper.valley.models.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue(value = "physical")
public class PhysicalStore extends Store {
	private String address;
}

