package com.recipe.application.dao;


import java.io.Serializable;

public class User implements Serializable {

    private String userName;

    private String image;
    private String label;
    private String name;

//    private BmobFile picture;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }



//    public BmobFile getPicture() {
//        return picture;
//    }
//
//    public void setPicture(BmobFile picture) {
//        this.picture = picture;
//    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", image='" + image + '\'' +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
