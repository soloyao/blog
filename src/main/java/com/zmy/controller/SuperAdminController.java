package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.service.ArticleLikesRecordService;
import com.zmy.service.ArticleService;
import com.zmy.service.CategoryService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

@RestController
public class SuperAdminController {
	@Autowired ArticleService articleService;
	@Autowired CategoryService categoryService;
	@Autowired ArticleLikesRecordService articleLikesRecordService;
	
	/**
	 * 分页获取文章管理文章
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@PostMapping("/getArticleManagement")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String getArticleManagement(@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
		}
		DataMap data = articleService.getArticleManagement(Integer.parseInt(rows), Integer.parseInt(pageNum));
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteArticle")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String deleteArticle(@RequestParam("id") String id) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
		}
		DataMap data = articleService.deleteArticle(Long.parseLong(id));
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 获得分类信息
	 * @return
	 */
	@GetMapping("/getArticleCategories")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String getArticleCategories() {
		DataMap data = categoryService.findAllCategories();
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 添加或删除分类
	 * @param categoryName
	 * @param type
	 * @return
	 */
	@PostMapping("/updateCategory")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String updateCategory(@RequestParam("categoryName") String categoryName,
			@RequestParam("type") int type) {
		DataMap data = categoryService.updateCategory(categoryName, type);
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 获得文章点赞信息
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@PostMapping("/getArticleThumbsUp")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String getArticleThumbsUp(@RequestParam("rows") int rows,
			@RequestParam("pageNum") int pageNum) {
		DataMap data = articleLikesRecordService.getArticleThumbsUp(rows, pageNum);
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 已读一条点赞信息
	 * @param id
	 * @return
	 */
	@GetMapping("/readThisThumbsUp")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String readThisThumbsUp(@RequestParam("id") int id) {
		DataMap data = articleLikesRecordService.readThisThumbsUp(id);
		return null;
	}
	
	@GetMapping("/readAllThumbsUp")
	@RequiresRoles("ROLE_SUPERADMIN")
	public String readAllThumbsUp() {
		DataMap data = articleLikesRecordService.readAllThumbsUp();
		return JsonResult.build(data).toJSON();
	}
	
}
