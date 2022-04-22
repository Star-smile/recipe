package com.code.recipe.controller;

import com.code.recipe.bean.MaterialBean;
import com.code.recipe.bean.MethodBean;
import com.code.recipe.bean.PostBean;
import com.code.recipe.bean.PublishBean;
import com.code.recipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @ResponseBody
    @RequestMapping(value="publish", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Map<String,String> publishController(@RequestBody PublishBean publishBean){
        System.out.print(publishBean);
        PostBean post=publishBean.getPost();
        List<MethodBean> methods=publishBean.getMethods();
        List<MaterialBean> materials=publishBean.getMaterials();
       String result=postService.publishService(post, materials, methods);
        Map<String,String> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

}
