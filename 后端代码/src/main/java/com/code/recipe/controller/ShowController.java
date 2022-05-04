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
    @RequestMapping(value="showMaterial", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<MaterialBean> > showMaterialMsg(@RequestParam("post_id") String post_id){
        List<MaterialBean> result=service.getMaterialMsgService(post_id);
        Map<String,List<MaterialBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="showMethod", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<MethodBean> > showMethodMsg(@RequestParam("post_id") String post_id){
        List<MethodBean> result=service.getMethodMsgService(post_id);
        Map<String,List<MethodBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="likeMessage", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<PostBean>> likeMsg(@RequestParam("name") String name){
        List<PostBean> result=service.getLikeMsgService(name);
        Map<String,List<PostBean>> map=new HashMap<>();
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

    @ResponseBody
    @RequestMapping(value="showComment", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<CommentBean>> showCommentMsg(@RequestParam("post_id") String post_id){
        List<CommentBean> result=service.getCommentMsgService(post_id);
        Map<String,List<CommentBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="showSameNavPost", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<PostBean>> showSameNavPostMsg(@RequestParam("nav_name") String nav_name){
        List<PostBean> result=service.getSameNavPost(nav_name);
        Map<String,List<PostBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="showUserPost", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String, List<PostBean>> showUserPostMsg(@RequestParam("user_id") String user_id){
        List<PostBean> result=service.getUserPostMsg(user_id);
        Map<String,List<PostBean>> map=new HashMap<>();
        map.put("result",result);
        return map;
    }


}
