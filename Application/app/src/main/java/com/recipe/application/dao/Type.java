package com.recipe.application.dao;


import java.io.Serializable;

public class Type implements Serializable {

    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type_name='" + type_name + '\'' +
                '}';
    }
}

