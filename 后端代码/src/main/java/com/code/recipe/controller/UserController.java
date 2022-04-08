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
        map.put("result",result);
        return map;
    }

    @ResponseBody
    @RequestMapping(value="register", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> register(@RequestParam("userID") String userID,
                                        @RequestParam("userPwd") String userPwd){
        boolean result=service.userRegisterService(userID,userPwd);
        Map<String,Boolean> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

}
