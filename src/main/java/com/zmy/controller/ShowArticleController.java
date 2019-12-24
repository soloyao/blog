package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.service.ArticleService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

/**
 * @ClassName:ShowArticleController
 * @Description:文章显示页面
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月24日 上午10:28:19
 */
@RestController
public class ShowArticleController {
	@Autowired ArticleService articleService;
	
	@PostMapping("/getArticleByArticleId")
	public String getArticleByArticleId(@RequestParam("articleId") String articleId) {
		Subject subject = SecurityUtils.getSubject();
		String phone = null;
		if (subject.isAuthenticated()) {
			phone = (String) subject.getPrincipal();
		}
		DataMap data = articleService.getArticleByArticleId(Long.parseLong(articleId), phone);
		return JsonResult.build(data).toJSON();
	}
}
