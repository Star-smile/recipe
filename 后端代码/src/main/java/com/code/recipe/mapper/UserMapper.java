package com.code.recipe.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int userLoginMapper(String userID, String userPwd);

    String seeUserId(String userID);

    int userRegisterMapper(String userID, String userPwd);

}