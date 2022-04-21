package com.recipe.application.dao;


import java.io.Serializable;
import java.util.List;



public class Post implements Serializable {

    private String name;

    private String cover;

    private String introduction;

    private String tip;

    private String user_time;



    private String collection;


    private String degree;

    private String people;

    private Brief_dish type;


    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Brief_dish getType() {
        return type;
    }

    public void setType(Brief_dish type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUser_time() {
        return user_time;
    }

    public void setUser_time(String user_time) {
        this.user_time = user_time;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Post{" +
                "name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", introduction='" + introduction + '\'' +
                ", tip='" + tip + '\'' +
                ", user_time='" + user_time + '\'' +
                ", collection='" + collection + '\'' +
                ", degree='" + degree + '\'' +
                ", people='" + people + '\'' +
                ", type=" + type +
                '}';
    }
}
