package com.recipe.application.dao;


import android.widget.TextView;

import java.io.Serializable;

public class Focus implements Serializable {

    private String name;
    private String who;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public  void setText(CharSequence text) {

    }



    @Override
    public String toString() {
        return "Focus{" +
                "name='" + name + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
    public void delete(){

    }
}
