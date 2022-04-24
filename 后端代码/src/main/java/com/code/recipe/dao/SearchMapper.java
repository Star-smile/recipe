package com.code.recipe.dao;

import com.code.recipe.bean.PostBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SearchMapper {

    List<PostBean> getSearchPostMsgMapperByName(String name);

    List<PostBean> getSearchPostMsgMapperByIntroduction(String name);

    ArrayList<String> getSearchPost_idByIngredient(String name);

    PostBean getSearchPostMsgByPost_id(String post_id);
}
