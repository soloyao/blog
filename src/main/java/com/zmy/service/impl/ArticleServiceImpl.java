package com.zmy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.mapper.ArticleMapper;
import com.zmy.pojo.Article;
import com.zmy.service.ArticleService;
import com.zmy.util.DataMap;
import com.zmy.util.StringAndArray;

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

}
