package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.component.JavaScriptCheck;
import com.zmy.constant.CodeType;
import com.zmy.constant.SiteOwner;
import com.zmy.pojo.Comment;
import com.zmy.pojo.CommentLikesRecord;
import com.zmy.service.CommentLikesRecordService;
import com.zmy.service.CommentService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;
import com.zmy.util.TimeUtil;

@RestController
public class CommentController {
	@Autowired CommentService commentService;
	@Autowired UserService userService;
	@Autowired CommentLikesRecordService commentLikesRecordService;
	
	/**
	 * 获取文章下的所有评论
	 * @param articleId
	 * @return
	 */
	@PostMapping("/getAllComment")
	public String getAllComment(@RequestParam("articleId") long articleId) {
		Subject subject = SecurityUtils.getSubject();
		String phone = null;
		if (subject.isAuthenticated()) {
			phone = (String) subject.getPrincipal();
		}
		DataMap data = commentService.findCommentByArticleId(articleId, phone);
		return JsonResult.build(data).toJSON();
	}
	
	@PostMapping("/publishComment")
	public String publishComment(Comment comment) {
		Subject subject = SecurityUtils.getSubject();
		String phone = (String) subject.getPrincipal();
		comment.setCommentDate(TimeUtil.getFormatDateForFive());
		int userId = userService.findIdByPhone(phone);
		comment.setAnswererId(userId);
		comment.setRespondentId(userService.findIdByPhone(SiteOwner.SITE_OWNER));
		comment.setCommentContent(JavaScriptCheck.javaScriptCheck(comment.getCommentContent()));
		
		commentService.insertComment(comment);
		
		DataMap data = commentService.findCommentByArticleId(comment.getArticleId(), phone);
		return JsonResult.build(data).toJSON();
	}
	
	@GetMapping("/addCommentLike")
	public String addCommentLike(@RequestParam("articleId") String articleId,
			@RequestParam("respondentId") String respondentId) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
		}
		String phone = (String) subject.getPrincipal();
		CommentLikesRecord commentLikesRecord = new CommentLikesRecord(Long.parseLong(articleId),
				Integer.parseInt(respondentId.substring(1)), userService.findIdByPhone(phone), TimeUtil.getFormatDateForFive());
		if (commentLikesRecordService.isLiked(commentLikesRecord.getArticleId(), commentLikesRecord.getpId(), phone)) {
			return JsonResult.fail(CodeType.MESSAGE_HAS_THUMBS_UP).toJSON();
		}
		DataMap data = commentService.updateLikeByArticleIdAndId(commentLikesRecord.getArticleId(),commentLikesRecord.getpId());
        commentLikesRecordService.insertCommentLikesRecord(commentLikesRecord);
        return JsonResult.build(data).toJSON();
	}
}
