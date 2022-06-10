package com.code.recipe.base;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract  class BaseTest {

    //1-1-构建post请求
    protected RequestBuilder getPostBuilder(String url, JSONObject jsonObject){
        RequestBuilder builder = MockMvcRequestBuilders
                .post(url)
                .content(jsonObject.toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        return builder;
    }
    //1-2-构建get请求
    protected RequestBuilder getGetBuilder(String url){
        RequestBuilder builder = MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        return builder;
    }
    //2-执行请求
    protected MvcResult performMVC(MockMvc mvc, RequestBuilder bulider ){
        try {
            MvcResult mvcResult = mvc.perform(bulider)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("code").value(200))
                    //.andDo(MockMvcResultHandlers.print())
                    .andReturn();
            return mvcResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}