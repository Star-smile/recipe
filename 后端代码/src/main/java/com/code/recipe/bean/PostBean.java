package com.code.recipe.bean;

import lombok.Data;

@Data
public class PostBean {
    private String post_id;
    private String user_id;
    private String nav_id;
    private String name;
    private String introduction;
    private String cover;
    private String use_time;
    private String degree;
    private String tip;
    private String c_time;
    private int favor;

}
