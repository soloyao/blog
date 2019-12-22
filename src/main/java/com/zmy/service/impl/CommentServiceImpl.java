package com.zmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.mapper.CommentMapper;
import com.zmy.pojo.Comment;
import com.zmy.service.ArticleService;
import com.zmy.service.CommentService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired CommentMapper commentMapper;
	@Autowired UserService userService;
	@Autowired ArticleService articleService;
	
	@Override
	public DataMap findFiveNewComment(int rows, int pageNum) {
		JSONObject returnJson = new JSONObject();
		PageHelper.startPage(pageNum, rows, "id desc");
		List<Comment> fiveComments = commentMapper.findFiveNewComment();
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(fiveComments);
		
		JSONArray jsonArry = new JSONArray();
		JSONObject json;
		for (Comment comment : fiveComments) {
			json = new JSONObject();
			if (comment.getpId() != 0) {
				comment.setCommentContent("@" + userService.findUsernameById(comment.getRespondentId()) + " " + comment.getCommentContent());
			}
			json.put("id", comment.getId());
			json.put("articleId", comment.getArticleId());
			json.put("answerer", userService.findUsernameById(comment.getAnswererId()));
			json.put("commentDate", comment.getCommmentDate());
			json.put("commentContent", comment.getCommentContent());
			json.put("articleTitle", articleService.findArticleTitleByArticleId(comment.getArticleId()).get("articleTitle"));
			jsonArry.add(json);
		}
		returnJson.put("result", jsonArry);
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
	public int commentNum() {
		return commentMapper.commentNum();
	}

}
