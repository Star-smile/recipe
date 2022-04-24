package com.code.recipe.service;

import com.code.recipe.bean.PostBean;
import com.code.recipe.dao.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    SearchMapper mapper;

    public List<PostBean> getSearchPostMsg(String name){
        List<PostBean> postBeanList=mapper.getSearchPostMsgMapperByName(name);
        List<PostBean> postBeans=mapper.getSearchPostMsgMapperByIntroduction(name);
        postBeanList.addAll(postBeans);
        List<String> list=mapper.getSearchPost_idByIngredient(name);
        for (String s : list) {
            PostBean postBean = mapper.getSearchPostMsgByPost_id(s);
            if(postBean!=null)
                postBeanList.add(postBean);
        }
        return postBeanList;
    }
}
