package com.code.recipe.controller;

import com.code.recipe.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserEventController {
    @Autowired
    UserEventService service;

    @ResponseBody
    @RequestMapping(value="addFocus", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> addFocus(@RequestParam("name") String name,
                                     @RequestParam("who") String who){
        boolean res=service.addFocusMsgService(name, who);
        Map<String,Boolean> map=new HashMap<>();
        map.put("data",res);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="removeFocus", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> removeFocus(@RequestParam("name") String name,
                                     @RequestParam("who") String who){
        boolean res=service.removeFocusMsgService(name, who);
        Map<String,Boolean> map=new HashMap<>();
        map.put("data",res);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="addLike", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,String> addLike(@RequestParam("post_id") String post_id,
                                           @RequestParam("name") String name){
        String res=service.addLikeMsgService(post_id, name);
        Map<String,String> map=new HashMap<>();
        map.put("data",res);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="removeLike", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> removeLike(@RequestParam("like_id") String like_id){
        boolean res=service.removeLikeMsgService(like_id);
        Map<String,Boolean> map=new HashMap<>();
        map.put("data",res);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="addComment", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,String> addComment(@RequestParam("post_id") String post_id,
                                         @RequestParam("name") String name,
                                         @RequestParam("content") String content,
                                         @RequestParam("image") String image){
        String res=service.addCommentMsgService(post_id, name, content, image);
        Map<String,String> map=new HashMap<>();
        map.put("data",res);
        return map;
    }

}
