package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.ArticleLikesRecord;

@Mapper
public interface ArticleLikesMapper {
	ArticleLikesRecord isLiked(long articleId, int likerId);
	void save(ArticleLikesRecord articleLikesRecord);
}
