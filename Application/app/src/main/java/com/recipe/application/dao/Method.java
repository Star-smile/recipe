package com.recipe.application.dao;


import java.io.Serializable;

public class Method implements Serializable {
    private String image;

    private String explain;
    private String sequence;

    private Post id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Post getId() {
        return id;
    }

    public void setId(Post id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Method{" +
                "image='" + image + '\'' +
                ", explain='" + explain + '\'' +
                ", sequence='" + sequence + '\'' +
                ", id=" + id +
                '}';
    }
}

