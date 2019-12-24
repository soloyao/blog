package com.zmy.service;

import com.zmy.util.DataMap;

public interface CommentService {
	DataMap findFiveNewComment(int rows, int pageNum);
	DataMap findCommentByArticleId(long articleId, String phone);
	int commentNum();
}
