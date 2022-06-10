package com.code.recipe.controller;

import net.minidev.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void login() throws Exception{
        //1-封装请求参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("15761437601", "mdzs");
       String jsonObject = JSONObject.toJSONString(paramMap);
        //2-构建请求
        RequestBuilder builder = MockMvcRequestBuilders.post("/api/shop/login").content(jsonObject).
                accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        //3-执行请求
        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }
    @After
    public void after(){

    }
}
