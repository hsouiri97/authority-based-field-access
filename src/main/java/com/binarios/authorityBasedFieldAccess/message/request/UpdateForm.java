package com.binarios.authorityBasedFieldAccess.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
