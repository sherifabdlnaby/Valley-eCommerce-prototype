package com.piper.valley.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class AddCompanyForm {
    @NotEmpty
    @Size(min = 2, max = 40)
    private String name="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
