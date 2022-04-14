package com.code.recipe.bean;

import lombok.Data;

import java.util.List;

@Data
public class PublishBean {
    private PostBean post;
    private List<MaterialBean> materials;
    private List<MethodBean> methods;
}
