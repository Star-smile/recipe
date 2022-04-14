package com.code.recipe.mapper;

import com.code.recipe.bean.MaterialBean;
import com.code.recipe.bean.MethodBean;
import com.code.recipe.bean.PostBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    int insetPostMsg(PostBean post);

    int insertMaterial(MaterialBean materialBean);

    int insertMethod(MethodBean methodBean);
}
