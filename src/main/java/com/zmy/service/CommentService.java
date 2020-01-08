package com.zmy.service;

import com.zmy.pojo.Comment;
import com.zmy.pojo.CommentLikesRecord;
import com.zmy.util.DataMap;

public interface CommentService {
	DataMap findFiveNewComment(int rows, int pageNum);
	DataMap findCommentByArticleId(long articleId, String phone);
	int commentNum();
	void insertComment(Comment comment);
	DataMap updateLikeByArticleIdAndId(long articleId, long pId);
	void deleteCommentByArticleId(long articleId);
	DataMap replyReplyReturn(Comment comment, String answerer, int answererId, String respondent);
}
