package com.zmy.service;

import com.zmy.pojo.ArticleLikesRecord;

public interface ArticleLikesRecordService {
	boolean isLiked(long articleId, String phone);
	void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord);
	void deleteArticleLikesRecordByArticleId(long articleId);
}
