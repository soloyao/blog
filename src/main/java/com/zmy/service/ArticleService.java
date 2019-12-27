package com.zmy.service;

import java.util.Map;

import com.zmy.pojo.Article;
import com.zmy.util.DataMap;

public interface ArticleService {
	DataMap findAllArticles(String rows, String pageNo);
	Map<String, String> findArticleTitleByArticleId(long articleId);
	int countArticle();
	int countArticleCategoryByCategory(String category);
	int countArticleArchiveByArchive(String archive);
	DataMap findArticleByCategory(String category, int rows, int pageNum);
	DataMap findArticleByArchive(String archive, int rows, int pageNum);
	DataMap findArticleByTag(String tag, int rows, int pageNum);
	DataMap getArticleByArticleId(long articleId, String phone);
	DataMap updateLikeByArticleId(long articleId);
	DataMap getArticleManagement(int rows, int pageNum);
	DataMap deleteArticle(long id);
	Article findArticleById(int id);
	DataMap getDraftArticle(Article article, String[] articleTags, int articleGrade);
}
