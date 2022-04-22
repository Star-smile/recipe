package com.code.recipe.controller;

import com.code.recipe.bean.NavBean;
import com.code.recipe.bean.TypeBean;
import com.code.recipe.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShowController {
    @Autowired
    ShowService service;

    @ResponseBody
    @RequestMapping(value="typeMessage", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,List<TypeBean>> typeMsg(){
        List<TypeBean> result=service.getTypeMsgService();
        Map<String,List<TypeBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="navMessage", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,List<NavBean>> navMsg(){
        List<NavBean> result=service.getNavMsgService();
        Map<String,List<NavBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

}
