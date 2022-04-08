package com.code.recipe.service;

import com.code.recipe.mapper.UserMapper;
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
}
