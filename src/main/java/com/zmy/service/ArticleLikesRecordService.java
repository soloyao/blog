package com.zmy.service;

import com.zmy.pojo.ArticleLikesRecord;
import com.zmy.util.DataMap;

public interface ArticleLikesRecordService {
	boolean isLiked(long articleId, String phone);
	void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord);
	void deleteArticleLikesRecordByArticleId(long articleId);
	DataMap getArticleThumbsUp(int rows, int pageNum);
	DataMap readThisThumbsUp(int id);
	DataMap readAllThumbsUp();
}
