package com.recipe.application.dao;

import java.lang.reflect.Type;
import java.util.Date;


public class Brief_dish extends Object {

    private String dish_name;
    //方块颜色
    private Integer color;

    private Type type_id;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public Type getType_id() {
        return type_id;
    }

    public void setType_id(Type type_id) {
        this.type_id = type_id;
    }
}
