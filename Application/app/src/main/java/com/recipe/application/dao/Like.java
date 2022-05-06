package com.recipe.application.dao;


import java.io.Serializable;

public class Like implements Serializable {
    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Post postId;
    private String name;

    @Override
    public String toString() {
        return "Like{" +
                "postId=" + postId +
                ", name='" + name + '\'' +
                '}';
    }
}
