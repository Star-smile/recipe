package com.recipe.application.dao;


import java.io.File;
import java.io.Serializable;

public class Comment implements Serializable {

    private String time;

    private String image;

    private String name;

    private Post id;
    private String content;
    private File picture;


    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }



    public Post getId() {
        return id;
    }

    public void setId(Post id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "time='" + time + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", picture=" + picture +
                '}';
    }
}

