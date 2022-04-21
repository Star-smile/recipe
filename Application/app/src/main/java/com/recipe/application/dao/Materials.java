package com.recipe.application.dao;


import java.io.Serializable;


public class Materials implements Serializable {

    private String ingredient;
    private String consumption;
    private Post id;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public Post getId() {
        return id;
    }

    public void setId(Post id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "ingredient='" + ingredient + '\'' +
                ", consumption='" + consumption + '\'' +
                ", id=" + id +
                '}';
    }
}

