package com.zmy.service;

import java.util.Map;

import com.zmy.pojo.Article;
import com.zmy.util.DataMap;

public interface ArticleService {
	DataMap findAllArticles(String rows, String pageNo);
	Map<String, String> findArticleTitleByArticleId(long articleId);
	int countArticle();
	int countArticleCategoryByCategory(String category);
	DataMap findArticleByCategory(String category, int rows, int pageNum);
}
