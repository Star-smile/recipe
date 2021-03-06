package com.code.recipe.service;

import com.code.recipe.bean.*;
import com.code.recipe.dao.ShowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowMapper mapper;

    public List<TypeBean> getTypeMsgService(){
        return mapper.getTypeMsgMapper();
    }

    public List<NavBean> getNavMsgService(){
        return mapper.getNavMsgMapper();
    }

    public PostBean getPostMsgService(String post_id){
        return mapper.getPostMsgMapper(post_id);
    }

    public List<MaterialBean> getMaterialMsgService(String post_id){
        return mapper.getMaterialMsgMapper(post_id);

    }

    public List<MethodBean> getMethodMsgService(String post_id){
        return mapper.getMethodMsgMapper(post_id);

    }

    public List<PostBean> getLikeMsgService(String name){
        List<LikeBean> list=mapper.getLikeMsgMapper(name);
        List<PostBean> postBeanList=null;
        for (LikeBean likeBean : list) {
            PostBean bean = mapper.getPostMsgMapper(likeBean.getPost_id());
            postBeanList.add(bean);
        }
        return postBeanList;
    }

    public List<FocusBean> getFocusMsgService(String who){
        return mapper.getFocusMsgMapper(who);
    }

    public List<FocusBean> getFanMsgService(String who){
        return mapper.getFanMsgMapper(who);
    }

    public boolean whetherExistLikeMsgService(String post_id,String name){
        int rs= mapper.whetherExistLikeMessageMapper(post_id, name);
        return rs==1;
    }

    public boolean whetherExistFocusMsgService(String name,String who){
        int rs= mapper.whetherExistFocusMessageMapper(name,who);
        return rs==1;
    }

    public List<CommentBean> getCommentMsgService(String post_id){
        return mapper.getCommentMsgMapper(post_id);
    }

    public List<PostBean> getSameNavPost(String nav_name){
        String nav_id=mapper.getNav_idMapper(nav_name);
        return mapper.getSameNavPost(nav_id);
    }


    public List<PostBean> getUserPostMsg(String user_id){
        return mapper.getUserPost(user_id);
    }
}
