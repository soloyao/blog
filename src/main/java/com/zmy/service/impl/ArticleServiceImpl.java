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
import com.zmy.mapper.ArticleMapper;
import com.zmy.pojo.Article;
import com.zmy.service.ArticleService;
import com.zmy.util.DataMap;
import com.zmy.util.StringAndArray;
import com.zmy.util.TimeUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired ArticleMapper articleMapper;
	
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

}
