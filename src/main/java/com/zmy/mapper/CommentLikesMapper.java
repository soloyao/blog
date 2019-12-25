package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.CommentLikesRecord;

@Mapper
public interface CommentLikesMapper {
	CommentLikesRecord isLiked(long articleId, long pId, int likerId);
	void save(CommentLikesRecord commentLikesRecord);
}
