package com.code.recipe.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int userLoginMapper(String userID, String userPwd);

    String seeUserId(String userID);

    int userRegisterMapper(String userID, String userPwd);

    int userResetMapper(String userID,String userPwd);

    int changeNameMapper(String user_id,String newName);

    int changeImageMapper(String user_id,String newImage);

}