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
	List<Article> findArticleByTag(String tag);
	Article getArticleByArticleId(long articleId);
	Article findArticleByArticleId(long articleId);
	void updateLikeByArticleId(long articleId);
	int findLikesByArticleId(long articleId);
	List<Article> getArticleManagement();
	void updateLastOrNextId(String lastOrNextStr, long updateId, long articleId);
	void updateArticleLastId(long lastArticleId, long articleId);
	void updateArticleNextId(long nextArticleId, long articleId);
	Article findAllArticleId(long id);
	void deleteByArticleId(long articleId);
	Article findArticleById(int id);
	Article getArticleUrlById(int id);
	void updateArticleById(Article article);
	Article findEndArticleId();
	void save(Article article);
}
