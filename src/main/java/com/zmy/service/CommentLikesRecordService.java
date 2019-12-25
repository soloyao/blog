package com.zmy.service;

import com.zmy.pojo.CommentLikesRecord;

public interface CommentLikesRecordService {
	boolean isLiked(long articleId, long pId, String phone);
	void insertCommentLikesRecord(CommentLikesRecord commentLikesRecord);
}
