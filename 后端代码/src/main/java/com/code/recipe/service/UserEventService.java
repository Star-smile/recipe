package com.code.recipe.service;

import com.code.recipe.dao.UserEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UserEventService {
    @Autowired
    UserEventMapper mapper;

    public boolean addFocusMsgService(String name,String who){
        int result= mapper.whetherExistNameAndWho(name, who);
        if(result==0){
            String focus_id= UUID.randomUUID().toString();
            int r=mapper.addFocusMsgMapper(focus_id,name,who);
            return r==1;
        }
        else{
            return false;
        }

    }

    public boolean removeFocusMsgService(String name,String who){
        int result= mapper.whetherExistNameAndWho(name, who);
        if(result==1){
            int r=mapper.removeFocusMsgMapper(name, who);
            return r==1;
        }
        else{
            return false;
        }

    }

    public String addLikeMsgService(String post_id,String name){
        int res=mapper.whetherExistLikeMsg(post_id, name);
        int rs=0;
        String like_id="";
        if(res==0){
            like_id= UUID.randomUUID().toString();
            Date utilDate = new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String c_time=dateFormat.format(utilDate );
            rs= mapper.addLikeMsgMapper(like_id,post_id,name,c_time);
        }
        if(rs==1){
            return like_id;
        }else{
            return null;
        }
    }

    public boolean removeLikeMsgService(String like_id){
        int result= mapper.whetherExistLike_id(like_id);
        if(result==1){
            int r=mapper.removeLikeMsgMapper(like_id);
            return r==1;
        }
        else{
            return false;
        }

    }

    public String addCommentMsgService(String post_id,String name,String content,String image){
        String comment_id=UUID.randomUUID().toString();
        Date utilDate = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String c_time=dateFormat.format(utilDate );
        int rs= mapper.addCommentMsgMapper(comment_id,post_id,name,content,image,c_time);
        if(rs==1){
            return comment_id;
        }else{
            return null;
        }
    }

}
