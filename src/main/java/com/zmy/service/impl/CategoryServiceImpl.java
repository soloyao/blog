package com.zmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zmy.mapper.CategoryMapper;
import com.zmy.service.ArticleService;
import com.zmy.service.CategoryService;
import com.zmy.util.DataMap;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired CategoryMapper categoryMapper;
	@Autowired ArticleService articleService;
	
	@Override
	public DataMap findCategoriesNameAndArticleNum() {
		List<String> categoryNames = categoryMapper.findCategoriesName();
		JSONObject categoryJson;
		JSONArray categoryJsonArray = new JSONArray();
		JSONObject returnJson = new JSONObject();
		for (String categoryName : categoryNames) {
			categoryJson = new JSONObject();
			categoryJson.put("categoryName", categoryName);
			categoryJson.put("categoryArticleNum", articleService.countArticleCategoryByCategory(categoryName));
			categoryJsonArray.add(categoryJson);
		}
		returnJson.put("result", categoryJsonArray);
		return DataMap.success().setData(returnJson);
	}

	@Override
	public int countCategoriesNum() {
		return categoryMapper.countCategoriesNum();
	}

}
