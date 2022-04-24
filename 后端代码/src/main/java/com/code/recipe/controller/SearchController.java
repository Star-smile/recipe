package com.code.recipe.controller;

import com.code.recipe.bean.PostBean;
import com.code.recipe.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    SearchService service;

    @ResponseBody
    @RequestMapping(value="searchPost", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,List<PostBean>> searchPostController(@RequestParam("name") String name){
        List<PostBean> postBeans=service.getSearchPostMsg(name);
        Map<String,List<PostBean>> map=new HashMap<>();
        map.put("data",postBeans);
        return map;
    }

}
