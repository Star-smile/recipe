package com.code.recipe.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEventMapper {

    int addFocusMsgMapper(String focus_id, String name,String who);
}
