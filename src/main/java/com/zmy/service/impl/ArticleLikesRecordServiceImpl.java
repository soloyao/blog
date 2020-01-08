package com.zmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.constant.CodeType;
import com.zmy.mapper.ArticleLikesMapper;
import com.zmy.pojo.ArticleLikesRecord;
import com.zmy.service.ArticleLikesRecordService;
import com.zmy.service.ArticleService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;

@Service
public class ArticleLikesRecordServiceImpl implements ArticleLikesRecordService {
	@Autowired ArticleLikesMapper articleLikesMapper;
	@Autowired UserService userService;
	@Autowired ArticleService articleService;
	
	@Override
	public boolean isLiked(long articleId, String phone) {
		return articleLikesMapper.isLiked(articleId, userService.findIdByPhone(phone)) != null;
	}

	@Override
	public void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord) {
		articleLikesMapper.save(articleLikesRecord);
	}

	@Override
	public void deleteArticleLikesRecordByArticleId(long articleId) {
		articleLikesMapper.deleteArticleLikesRecordByArticleId(articleId);
	}

	@Override
	public DataMap getArticleThumbsUp(int rows, int pageNum) {
		JSONObject returnJson = new JSONObject();
		PageHelper.startPage(pageNum, rows);
		List<ArticleLikesRecord> likesRecords = articleLikesMapper.getArticleThumbsUp();
		PageInfo<ArticleLikesRecord> pageInfo = new PageInfo<ArticleLikesRecord>(likesRecords);
		JSONArray returnJsonArray = new JSONArray();
		JSONObject articleLikesJson;
		for (ArticleLikesRecord a : likesRecords) {
			articleLikesJson = new JSONObject();
			articleLikesJson.put("id", a.getId());
			articleLikesJson.put("articleId", a.getArticleId());
			articleLikesJson.put("likeDate", a.getLikeDate());
			articleLikesJson.put("praisePeople", userService.findUsernameById(a.getLikerId()));
			articleLikesJson.put("articleTitle", articleService.findArticleTitleByArticleId(a.getArticleId()).get("articleTitle"));
			articleLikesJson.put("isRead", a.getIsRead());
			returnJsonArray.add(articleLikesJson);
		}
		returnJson.put("result", returnJsonArray);
		returnJson.put("msgIsNotReadNum", articleLikesMapper.countIsReadNum());
		
		JSONObject pageJson = new JSONObject();
		pageJson.put("pageNum", pageInfo.getPageNum());
		pageJson.put("pageSize", pageInfo.getPageSize());
		pageJson.put("total", pageInfo.getTotal());
		pageJson.put("pages", pageInfo.getPages());
		pageJson.put("isFirstPage", pageInfo.isIsFirstPage());
		pageJson.put("isLastPage", pageInfo.isIsLastPage());
		returnJson.put("pageInfo", pageJson);
		
		return DataMap.success().setData(returnJson);
	}

	@Override
	public DataMap readThisThumbsUp(int id) {
		try {
			articleLikesMapper.readThisThumbsUp(id);
			return DataMap.success();
		} catch (Exception e) {
			return DataMap.fail(CodeType.READ_ARTICLE_THUMBS_UP_FAIL);
		}
	}

	@Override
	public DataMap readAllThumbsUp() {
		try {
			articleLikesMapper.readAllThumbsUp();
			return DataMap.success();
		} catch (Exception e) {
			return DataMap.fail(CodeType.READ_ARTICLE_THUMBS_UP_FAIL);
		}
	}

}
