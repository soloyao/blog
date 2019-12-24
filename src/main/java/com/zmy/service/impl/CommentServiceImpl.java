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
import com.zmy.service.CommentLikesRecordService;
import com.zmy.service.CommentService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired CommentMapper commentMapper;
	@Autowired UserService userService;
	@Autowired ArticleService articleService;
	@Autowired CommentLikesRecordService commentLikesRecordService;
	
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
			json.put("commentDate", comment.getCommentDate());
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

	@Override
	public DataMap findCommentByArticleId(long articleId, String phone) {
		List<Comment> commentLists = commentMapper.findCommentByArticleIdAndPid(articleId, 0);
		JSONArray commentJsonArray = new JSONArray();
		JSONArray replyJsonArray;
		JSONObject commentJsonObject;
		JSONObject replyJsonObject;
		List<Comment> replyLists;
		for (Comment comment : commentLists) {
			commentJsonObject = new JSONObject();
			
			replyLists = commentMapper.findCommentByArticleIdAndPidNoOrder(articleId, comment.getId());
			
			replyJsonArray = new JSONArray();
			//封装所有评论中的回复
			for (Comment reply : replyLists) {
				replyJsonObject = new JSONObject();
				replyJsonObject.put("id", reply.getId());
				replyJsonObject.put("answerer", userService.findUsernameById(reply.getAnswererId()));
				replyJsonObject.put("commentDate", reply.getCommentDate());
				replyJsonObject.put("commentContent", reply.getCommentContent());
				replyJsonObject.put("respondent", userService.findUsernameById(reply.getRespondentId()));
				replyJsonArray.add(replyJsonObject);
			}
			
			//封装评论
			commentJsonObject.put("id", comment.getId());
			commentJsonObject.put("answerer", userService.findUsernameById(comment.getAnswererId()));
			commentJsonObject.put("commentDate", comment.getCommentDate());
			commentJsonObject.put("likes", comment.getLikes());
			commentJsonObject.put("commentContent", comment.getCommentContent());
			commentJsonObject.put("replies", replyJsonArray);
			commentJsonObject.put("avatarImgUrl", userService.getHeadPortraitUrl(comment.getAnswererId()));
			
			if (phone == null) {
				commentJsonObject.put("isLiked", 0);
			} else {
				if (commentLikesRecordService.isLiked(articleId, comment.getId(), phone)) {
					commentJsonObject.put("isLiked", 1);
				} else {
					commentJsonObject.put("isLiked", 0);
				}
			}
			commentJsonArray.add(commentJsonObject);
		}
		return DataMap.success().setData(commentJsonArray);
	}

}
