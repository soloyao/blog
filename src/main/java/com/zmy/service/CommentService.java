package com.zmy.service;

import com.zmy.util.DataMap;

public interface CommentService {
	DataMap findFiveNewComment(int rows, int pageNum);
	int commentNum();
}
