package com.piper.valley.models.domain;
import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Admin extends User {

}

