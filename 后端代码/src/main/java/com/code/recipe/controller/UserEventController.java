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
    @RequestMapping(value="focus", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Map<String,Boolean> login(@RequestParam("name") String name,
                                     @RequestParam("who") String who){

    }
}
