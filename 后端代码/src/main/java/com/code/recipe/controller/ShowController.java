package com.code.recipe.controller;

import com.code.recipe.bean.*;
import com.code.recipe.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ResponseBody
    @RequestMapping(value="postMessage", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, PostBean> postMsg(@RequestParam("post_id") String post_id){
        PostBean result=service.getPostMsgService(post_id);
        Map<String,PostBean> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="likeMessage", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<LikeBean>> likeMsg(@RequestParam("name") String name){
        List<LikeBean> result=service.getLikeMsgService(name);
        Map<String,List<LikeBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="focusMessage", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<FocusBean>> focusMsg(@RequestParam("who") String who){
        List<FocusBean> result=service.getFocusMsgService(who);
        Map<String,List<FocusBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="whetherLike", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, Boolean > likeExistMsg(@RequestParam("post_id") String post_id,
                                             @RequestParam("name") String name){
        Boolean result=service.whetherExistLikeMsgService(post_id, name);
        Map<String,Boolean> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="whetherFocus", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, Boolean > focusExistMsg(@RequestParam("name") String name,
                                               @RequestParam("who") String who){
        Boolean result=service.whetherExistFocusMsgService(name, who);
        Map<String,Boolean> map=new HashMap<>();
        map.put("result",result);
        return map;
    }


}
