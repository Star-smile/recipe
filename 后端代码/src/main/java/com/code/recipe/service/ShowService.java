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

    public List<LikeBean> getLikeMsgService(String name){
        return mapper.getLikeMsgMapper(name);
    }

    public List<FocusBean> getFocusMsgService(String who){
        return mapper.getFocusMsgMapper(who);
    }

    public boolean whetherExistLikeMsgService(String post_id,String name){
        int rs= mapper.whetherExistLikeMessageMapper(post_id, name);
        return rs==1;
    }

    public boolean whetherExistFocusMsgService(String name,String who){
        int rs= mapper.whetherExistFocusMessageMapper(name,who);
        return rs==1;
    }
}
