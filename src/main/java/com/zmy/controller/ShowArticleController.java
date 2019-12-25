package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.pojo.ArticleLikesRecord;
import com.zmy.service.ArticleLikesRecordService;
import com.zmy.service.ArticleService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;
import com.zmy.util.TimeUtil;

/**
 * @ClassName:ShowArticleController
 * @Description:文章显示页面
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月24日 上午10:28:19
 */
@RestController
public class ShowArticleController {
	@Autowired ArticleService articleService;
	@Autowired UserService userService;
	@Autowired ArticleLikesRecordService articleLikesRecordService;
	
	/**
	 * 根据ID获取文章
	 * @param articleId
	 * @return
	 */
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
	
	@GetMapping("/addArticleLike")
	public String addArticleLike(@RequestParam("articleId") String articleId) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
		}
		String phone = (String) subject.getPrincipal();
		int userId = userService.findIdByPhone(phone);
		if (articleLikesRecordService.isLiked(Long.parseLong(articleId), phone)) {
			return JsonResult.fail(CodeType.ARTICLE_HAS_THUMBS_UP).toJSON();
		}
		
		DataMap data = articleService.updateLikeByArticleId(Long.parseLong(articleId));
		
		ArticleLikesRecord articleLikesRecord = new ArticleLikesRecord(Long.parseLong(articleId), userId, TimeUtil.getFormatDateForFive());
		articleLikesRecordService.insertArticleLikesRecord(articleLikesRecord);
		
		return JsonResult.build(data).toJSON();
	}
}
