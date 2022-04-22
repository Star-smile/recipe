package com.code.recipe.dao;

import com.code.recipe.bean.NavBean;
import com.code.recipe.bean.TypeBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShowMapper {

    List<TypeBean> getTypeMsgMapper();

    List<NavBean> getNavMsgMapper();
}
