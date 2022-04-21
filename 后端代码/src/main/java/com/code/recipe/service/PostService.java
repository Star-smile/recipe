package com.code.recipe.service;

import com.code.recipe.bean.MaterialBean;
import com.code.recipe.bean.MethodBean;
import com.code.recipe.bean.PostBean;
import com.code.recipe.dao.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    PostMapper mapper;

    public String publishService(PostBean post, List<MaterialBean> materials, List<MethodBean> methods){
        boolean tmp=false;
        String post_id= UUID.randomUUID().toString();
        Date utilDate = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(utilDate );
        post.setPost_id(post_id);
        post.setC_time(time);
        int ret=mapper.insetPostMsg(post);
        if(ret==1){
            for (MaterialBean material : materials) {
                String material_id = UUID.randomUUID().toString();
                material.setPost_id(post_id);
                material.setMaterial_id(material_id);
                int res=mapper.insertMaterial(material);
                tmp= res == 1;
            }
            for (MethodBean method : methods) {
                String method_id = UUID.randomUUID().toString();
                method.setPost_id(post_id);
                method.setMethod_id(method_id);
                int r=mapper.insertMethod(method);
                tmp=r==1;
            }
        }
        if(tmp)
            return post_id;
        else
            return null;
    }
}
