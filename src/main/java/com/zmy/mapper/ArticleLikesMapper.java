package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.ArticleLikesRecord;

@Mapper
public interface ArticleLikesMapper {
	ArticleLikesRecord isLiked(long articleId, int likerId);
	void save(ArticleLikesRecord articleLikesRecord);
	void deleteArticleLikesRecordByArticleId(long articleId);
	List<ArticleLikesRecord> getArticleThumbsUp();
	int countIsReadNum();
	void readThisThumbsUp(int id);
	void readAllThumbsUp();
}
