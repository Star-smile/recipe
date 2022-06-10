package com.code.recipe.controller;

import com.code.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @ResponseBody
    @RequestMapping(value="login", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> login(@RequestParam("userID") String userID,
                                        @RequestParam("userPwd") String userPwd){
        boolean result=service.userLoginService(userID,userPwd);
        Map<String,Boolean> map=new HashMap<>();
        map.put("data",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="register", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Map<String,String> register(@RequestParam("userID") String userID,
                                        @RequestParam("userPwd") String userPwd){
        String result=service.userRegisterService(userID,userPwd);
        Map<String,String> map=new HashMap<>();
        map.put("data",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="reset", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> reset(@RequestParam("userID") String userID,
                                     @RequestParam("userNewPwd") String userNewPwd){
        boolean result=service.userResetService(userID,userNewPwd);
        Map<String,Boolean> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="changeName", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> changeName(@RequestParam("user_id") String user_id,
                                     @RequestParam("newName") String newName){
        boolean result=service.changeNameService(user_id, newName);
        Map<String,Boolean> map=new HashMap<>();
        map.put("data",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="changeImage", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> changeImage(@RequestParam("user_id") String user_id,
                                          @RequestParam("newImage") String newImage){
        boolean result=service.changeImageService(user_id, newImage);
        Map<String,Boolean> map=new HashMap<>();
        map.put("data",result);
        return map;
    }
}
