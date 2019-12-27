package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.service.ArticleService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

@RestController
public class SuperAdminController {
	@Autowired ArticleService articleService;
	
	/**
	 * 分页获取文章管理文章
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@PostMapping("/getArticleManagement")
	public String getArticleManagement(@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
		}
		DataMap data = articleService.getArticleManagement(Integer.parseInt(rows), Integer.parseInt(pageNum));
		return JsonResult.build(data).toJSON();
	}
	
	@GetMapping("/deleteArticle")
	public String deleteArticle(@RequestParam("id") String id) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
		}
		DataMap data = articleService.deleteArticle(Long.parseLong(id));
		return JsonResult.build(data).toJSON();
	}
}
