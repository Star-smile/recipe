package com.code.recipe.bean;

import lombok.Data;

@Data
public class CommentBean {

    private String comment_id;
    private String post_id;
    private String name;
    private String content;
    private String image;
    private String c_time;
}
