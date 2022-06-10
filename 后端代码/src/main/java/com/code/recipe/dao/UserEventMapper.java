package com.code.recipe.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEventMapper {

    int whetherExistFocus_id(String focus_id);

    int whetherExistNameAndWho(String name, String who);

    int addFocusMsgMapper(String focus_id, String name,String who);

    int removeFocusMsgMapper(String focus_id);

    int whetherExistLikeMsg(String post_id,String name);

    int addLikeMsgMapper(String like_id,String post_id,String name,String c_time);

    int whetherExistLike_id(String like_id);

    int removeLikeMsgMapper(String like_id);

    int addCommentMsgMapper(String comment_id,String post_id,String name,String content,String image,String c_time);

    int whetherExistComment_id(String comment_id);

    int removeCommentMsgMapper(String comment_id);
}
