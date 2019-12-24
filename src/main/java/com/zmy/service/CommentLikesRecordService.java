package com.zmy.service;

public interface CommentLikesRecordService {
	boolean isLiked(long articleId, long pId, String phone);
}
