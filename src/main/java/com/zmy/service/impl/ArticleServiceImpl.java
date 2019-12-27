package com.zmy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.constant.CodeType;
import com.zmy.mapper.ArticleMapper;
import com.zmy.pojo.Article;
import com.zmy.service.ArticleLikesRecordService;
import com.zmy.service.ArticleService;
import com.zmy.service.CommentLikesRecordService;
import com.zmy.service.CommentService;
import com.zmy.service.VisitorService;
import com.zmy.util.DataMap;
import com.zmy.util.StringAndArray;
import com.zmy.util.TimeUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired ArticleMapper articleMapper;
	@Autowired ArticleLikesRecordService articleLikesRecordService;
	@Autowired VisitorService visitorService;
	@Autowired CommentService commentService;
	@Autowired CommentLikesRecordService commentLikesRecordService;
	
	@Override
	public DataMap findAllArticles(String rows, String pageNo) {
		int pageNum = Integer.parseInt(pageNo);
		int pageSize = Integer.parseInt(rows);
		
		PageHelper.startPage(pageNum, pageSize, "id desc");
		List<Article> articles = articleMapper.findAllArticles();
		PageInfo<Article> page = new PageInfo<Article>(articles);
		JSONObject json;
		JSONArray jsonArray = new JSONArray();
		for (Article article : articles) {
			json = new JSONObject();
			json.put("thisArticleUrl", "/article/" + article.getArticleId());
			json.put("articleTags", StringAndArray.stringToArray(article.getArticleTags()));
			json.put("articleTitle", article.getArticleTitle());
			json.put("articleType", article.getArticleType());
			json.put("publishDate", article.getPublishDate());
			json.put("originalAuthor", article.getOriginalAuthor());
			json.put("articleCategories", article.getArticleCategories());
			json.put("articleTabloid", article.getArticleTabloid());
			json.put("likes", article.getLikes());
			jsonArray.add(json);
		}
		JSONObject thisPageInfo = new JSONObject();
		thisPageInfo.put("pageNum", page.getPageNum());
		thisPageInfo.put("pageSize", page.getPageSize());
		thisPageInfo.put("total", page.getTotal());
		thisPageInfo.put("pages", page.getPages());
		thisPageInfo.put("isFirstPage", page.isIsFirstPage());
		thisPageInfo.put("isLastPage", page.isIsLastPage());
		
		jsonArray.add(thisPageInfo);
		return DataMap.success().setData(jsonArray);
	}

	@Override
	public Map<String, String> findArticleTitleByArticleId(long articleId) {
		Article article = articleMapper.findArticleTitleByArticleId(articleId);
		Map<String, String> articleMap = new HashMap<String, String>();
		if (article != null) {
			articleMap.put("articleTitle", article.getArticleTitle());
			articleMap.put("articleTabloid", article.getArticleTabloid());
		}
		return articleMap;
	}

	@Override
	public int countArticle() {
		return articleMapper.countArticle();
	}

	@Override
	public int countArticleCategoryByCategory(String category) {
		return articleMapper.countArticleCategoryByCategory(category);
	}

	@Override
	public DataMap findArticleByCategory(String category, int rows, int pageNum) {
		List<Article> articles;
		PageInfo<Article> pageInfo;
		JSONArray articleJsonArray = new JSONArray();
		PageHelper.startPage(pageNum, rows);
		if (StringUtils.isEmpty(category)) {
			articles = articleMapper.findAllArticlePartInfo();
			category = "全部分类";
		} else {
			 articles = articleMapper.findArticleByCategory(category);
		}
		pageInfo = new PageInfo<Article>(articles);
		
		articleJsonArray = timeLineReturn(articleJsonArray, articles);
		
		JSONObject pageJson = new JSONObject();
		pageJson.put("pageNum", pageInfo.getPageNum());
		pageJson.put("pageSize", pageInfo.getPageSize());
		pageJson.put("total", pageInfo.getTotal());
		pageJson.put("pages", pageInfo.getPages());
		pageJson.put("isFirstPage", pageInfo.isIsFirstPage());
		pageJson.put("isLastPage", pageInfo.isIsLastPage());
		
		JSONObject json = new JSONObject();
		json.put("result", articleJsonArray);
		json.put("category", category);
		json.put("pageInfo", pageJson);
		
		return DataMap.success().setData(json);
	}
	
	@Override
	public DataMap findArticleByArchive(String archive, int rows, int pageNum) {
		List<Article> articles;
		PageInfo<Article> pageInfo;
		JSONArray articleJsonArray = new JSONArray();
		PageHelper.startPage(pageNum, rows);
		String showMonth = "hide";
		if (!StringUtils.isEmpty(archive)) {
			archive = TimeUtil.timeYearToWhippletree(archive);
		}
		if (StringUtils.isEmpty(archive)) {
			articles = articleMapper.findAllArticlePartInfo();
		} else {
			articles = articleMapper.findArticleByArchive(archive);
			showMonth = "show";
		}
		pageInfo = new PageInfo<Article>(articles);
		
		articleJsonArray = timeLineReturn(articleJsonArray, articles);
		
		JSONObject pageJson = new JSONObject();
		pageJson.put("pageNum", pageInfo.getPageNum());
		pageJson.put("pageSize", pageInfo.getPageSize());
		pageJson.put("total", pageInfo.getTotal());
		pageJson.put("pages", pageInfo.getPages());
		pageJson.put("isFirstPage", pageInfo.isIsFirstPage());
		pageJson.put("isLastPage", pageInfo.isIsLastPage());
		
		JSONObject json = new JSONObject();
		json.put("result", articleJsonArray);
		json.put("articleNum", articleMapper.countArticle());
		json.put("pageInfo", pageJson);
		json.put("showMonth", showMonth);
		
		return DataMap.success().setData(json);
	}
	
	@Override
	public DataMap findArticleByTag(String tag, int rows, int pageNum) {
		PageHelper.startPage(pageNum, rows);
		List<Article> articles = articleMapper.findArticleByTag(tag);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
		JSONObject articleJson;
		JSONArray articleJsonArray = new JSONArray();
		//二次判断标签是否匹配
		for (Article article : articles) {
			String[] tagsArray = StringAndArray.stringToArray(article.getArticleTags());
			for (String str : tagsArray) {
				if (str.equals(tag)) {
					articleJson = new JSONObject();
					articleJson.put("articleId", article.getArticleId());
					articleJson.put("originalAuthor", article.getOriginalAuthor());
					articleJson.put("articleTitle", article.getArticleTitle());
					articleJson.put("articleCategories", article.getArticleCategories());
					articleJson.put("publishDate", article.getPublishDate());
					articleJson.put("articleTags", tagsArray);
					articleJsonArray.add(articleJson);
				}
			}
		}
		
		JSONObject pageJson = new JSONObject();
		pageJson.put("pageNum", pageInfo.getPageNum());
		pageJson.put("pageSize", pageInfo.getPageSize());
		pageJson.put("total", pageInfo.getTotal());
		pageJson.put("pages", pageInfo.getPages());
		pageJson.put("isFirstPage", pageInfo.isIsFirstPage());
		pageJson.put("isLastPage", pageInfo.isIsLastPage());
		
		JSONObject returnJson = new JSONObject();
		returnJson.put("result", articleJsonArray);
		returnJson.put("tag", tag);
		returnJson.put("pageInfo", pageJson);
		return DataMap.success(CodeType.FIND_ARTICLE_BY_TAG).setData(returnJson);
	}
	
	private JSONArray timeLineReturn(JSONArray articleJsonArray, List<Article> articles) {
		JSONObject articleJson;
		for (Article article : articles) {
			String[] tagsArray = StringAndArray.stringToArray(article.getArticleTags());
			articleJson = new JSONObject();
			articleJson.put("articleId", article.getArticleId());
			articleJson.put("originalAuthor", article.getOriginalAuthor());
			articleJson.put("articleTitle", article.getArticleTitle());
			articleJson.put("articleCategories", article.getArticleCategories());
			articleJson.put("publishDate", article.getPublishDate());
			articleJson.put("articleTags", tagsArray);
			articleJsonArray.add(articleJson);
		}
		return articleJsonArray;
	}

	@Override
	public int countArticleArchiveByArchive(String archive) {
		return articleMapper.countArticleArchiveByArchive(archive);
	}

	@Override
	public DataMap getArticleByArticleId(long articleId, String phone) {
		Article article = articleMapper.getArticleByArticleId(articleId);
		if (article != null) {
			Map<String, Object> dataMap = new HashMap<String, Object>(32);
			Article lastArticle = articleMapper.findArticleByArticleId(article.getLastArticleId());
			Article nextArticle = articleMapper.findArticleByArticleId(article.getNextArticleId());
			dataMap.put("author", article.getAuthor());
			dataMap.put("articleId", articleId);
			dataMap.put("originalAuthor", article.getOriginalAuthor());
			dataMap.put("articleTitle", article.getArticleTitle());
			dataMap.put("publishDate", article.getPublishDate());
			dataMap.put("updateDate", article.getUpdateDate());
			dataMap.put("articleContent", article.getArticleContent());
			dataMap.put("articleTags", StringAndArray.stringToArray(article.getArticleTags()));
			dataMap.put("articleType", article.getArticleType());
			dataMap.put("articleCategories", article.getArticleCategories());
			dataMap.put("articleUrl", article.getArticleUrl());
			dataMap.put("likes", article.getLikes());
			if (phone == null) {
				dataMap.put("isLiked", 0);
			} else {
				if (articleLikesRecordService.isLiked(articleId, phone)) {
					dataMap.put("isLiked", 1);
				} else {
					dataMap.put("isLiked", 0);
				}
			}
			if (lastArticle != null) {
				dataMap.put("lastStatus", "200");
				dataMap.put("lastArticleTitle", lastArticle.getArticleTitle());
				dataMap.put("lastArticleUrl", "/article/" + lastArticle.getArticleId());
			} else {
				dataMap.put("lastStatus", "500");
				dataMap.put("lastInfo", "无");
			}
			if (nextArticle != null) {
				dataMap.put("nextStatus", "200");
				dataMap.put("nextArticleTitle", nextArticle.getArticleTitle());
				dataMap.put("nextArticleUrl", "/article/" + nextArticle.getArticleId());
			} else {
				dataMap.put("nextStatus", "500");
				dataMap.put("nextInfo", "无");
			}
			return DataMap.success().setData(dataMap);
		}
		return DataMap.fail(CodeType.ARTICLE_NOT_EXIST);
	}

	@Override
	public DataMap updateLikeByArticleId(long articleId) {
		articleMapper.updateLikeByArticleId(articleId);
		int likes = articleMapper.findLikesByArticleId(articleId);
		return DataMap.success().setData(likes);
	}

	@Override
	public DataMap getArticleManagement(int rows, int pageNum) {
		PageHelper.startPage(pageNum, rows);
		List<Article> articles = articleMapper.getArticleManagement();
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
		JSONArray returnJsonArray = new JSONArray();
		JSONObject returnJson = new JSONObject();
		JSONObject articleJson;
		for (Article article : articles) {
			articleJson = new JSONObject();
			articleJson.put("id", article.getId());
			articleJson.put("articleId", article.getArticleId());
			articleJson.put("originalAuthor", article.getOriginalAuthor());
			articleJson.put("articleTitle", article.getArticleTitle());
			articleJson.put("articleCategories", article.getArticleCategories());
			articleJson.put("publishDate", article.getPublishDate());
			String pageName = "article/" + article.getArticleId();
			articleJson.put("visitorNum", visitorService.getNumByPageName(pageName));
			
			returnJsonArray.add(articleJson);
		}
		returnJson.put("result", returnJsonArray);
		JSONObject pageJson = new JSONObject();
		pageJson.put("pageNum",pageInfo.getPageNum());
        pageJson.put("pageSize",pageInfo.getPageSize());
        pageJson.put("total",pageInfo.getTotal());
        pageJson.put("pages",pageInfo.getPages());
        pageJson.put("isFirstPage",pageInfo.isIsFirstPage());
        pageJson.put("isLastPage",pageInfo.isIsLastPage());

        returnJson.put("pageInfo",pageJson);
        
        return DataMap.success().setData(returnJson);
	}

	@Override
	public DataMap deleteArticle(long id) {
		try {
			Article deleteArticle = articleMapper.findAllArticleId(id);
			articleMapper.updateLastOrNextId("lastArticleId", deleteArticle.getLastArticleId(), deleteArticle.getNextArticleId());
			articleMapper.updateLastOrNextId("nextArticleId", deleteArticle.getNextArticleId(), deleteArticle.getLastArticleId());
			//删除本篇文章
			articleMapper.deleteByArticleId(deleteArticle.getArticleId());
			//删除与该文章有关的所有文章点赞记录、文章评论、文章评论记录
			commentService.deleteCommentByArticleId(deleteArticle.getArticleId());
			commentLikesRecordService.deleteCommentLikesRecordByArticleId(deleteArticle.getArticleId());
			articleLikesRecordService.deleteArticleLikesRecordByArticleId(deleteArticle.getArticleId());
		} catch (Exception e) {
			e.printStackTrace();
			return DataMap.fail(CodeType.DELETE_ARTICLE_FAIL);
		}
		return DataMap.success();
	}

	@Override
	public Article findArticleById(int id) {
		return articleMapper.findArticleById(id);
	}

	@Override
	public DataMap getDraftArticle(Article article, String[] articleTags, int articleGrade) {
		Map<String, Object> dataMap = new HashMap<String, Object>(16);
		dataMap.put("id", article.getId());
		dataMap.put("articleTitle", article.getArticleTitle());
		dataMap.put("articleType", article.getArticleType());
		dataMap.put("articleCategories", article.getArticleCategories());
		dataMap.put("articleUrl", article.getArticleUrl());
		dataMap.put("originalAuthor", article.getOriginalAuthor());
		dataMap.put("articleContent", article.getArticleContent());
		dataMap.put("articleTags", articleTags);
		dataMap.put("articleGrade", articleGrade);
		return DataMap.success().setData(dataMap);
	}

}
