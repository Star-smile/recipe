package com.code.recipe.dao;

import com.code.recipe.bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShowMapper {

    List<TypeBean> getTypeMsgMapper();

    List<NavBean> getNavMsgMapper();

    PostBean getPostMsgMapper(String post_id);

    List<MaterialBean> getMaterialMsgMapper(String post_id);

    List<MethodBean> getMethodMsgMapper(String post_id);

    List<LikeBean> getLikeMsgMapper(String name);

    List<FocusBean> getFocusMsgMapper(String who);

    List<CommentBean> getCommentMsgMapper(String post_id);

    int whetherExistLikeMessageMapper(String post_id,String name);

    int whetherExistFocusMessageMapper(String name,String who);

    String getNav_idMapper(String nav_name);

    List<PostBean>  getSameNavPost(String nav_id);

}
