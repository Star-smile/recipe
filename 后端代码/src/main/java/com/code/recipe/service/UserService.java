package com.code.recipe.service;

import com.code.recipe.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper mapper;

    public boolean userLoginService(String userID, String userPwd) {
        int res = mapper.userLoginMapper(userID, userPwd);
        return res == 1;
    }

    public boolean userRegisterService(String userID, String userPwd){
        String  userId=mapper.seeUserId(userID);
        if(userId != null){
            return false;
        }else{
            int res= mapper.userRegisterMapper(userID, userPwd);
            return res==1;
        }
    }

    public boolean userResetService(String userID,String userNewPwd){
        String  userId=mapper.seeUserId(userID);
        if(userId != null){
            int res=mapper.userResetMapper(userID,userNewPwd);
            return res==1;
        }else{
            return false;
        }
    }

    public boolean changeNameService(String user_id,String newName){
        String  userId=mapper.seeUserId(user_id);
        if(userId != null){
            int res=mapper.changeNameMapper(user_id, newName);
            return res==1;
        }else{
            return false;
        }
    }

    public boolean changeImageService(String user_id,String newImage){
        String userID=mapper.seeUserId(user_id);
        if(userID!=null){
            int res= mapper.changeImageMapper(user_id, newImage);
            return res==1;
        }
        else{
            return false;
        }
    }

}
