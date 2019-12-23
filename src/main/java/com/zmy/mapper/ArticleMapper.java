package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.Article;

@Mapper
public interface ArticleMapper {
	List<Article> findAllArticles();
	Article findArticleTitleByArticleId(long articleId);
	int countArticle();
	int countArticleCategoryByCategory(String category);
	int countArticleArchiveByArchive(String archive);
	List<Article> findAllArticlePartInfo();
	List<Article> findArticleByCategory(String category);
	List<Article> findArticleByArchive(String archive);
}
