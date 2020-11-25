package com.yuekehoutai.domain.param;

import javax.validation.constraints.NotNull;

public class ActTypeParam {
    @NotNull
    private Integer id;
    @NotNull
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
