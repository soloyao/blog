package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.service.CommentService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

@RestController
public class CommentController {
	@Autowired CommentService commentService;
	
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
}
