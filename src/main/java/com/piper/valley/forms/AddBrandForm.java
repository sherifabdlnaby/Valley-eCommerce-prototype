package com.piper.valley.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class AddBrandForm {

    @NotEmpty

    private String name="";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "AddBrandForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
