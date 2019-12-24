package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.Comment;

@Mapper
public interface CommentMapper {
	List<Comment> findFiveNewComment();
	int commentNum();
	List<Comment> findCommentByArticleIdAndPid(long articleId, long pId);
	List<Comment> findCommentByArticleIdAndPidNoOrder(long articleId, long pId);
}
