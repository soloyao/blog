package com.zmy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.pojo.Article;
import com.zmy.service.ArticleService;
import com.zmy.service.CategoryService;
import com.zmy.service.TagService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;
import com.zmy.util.StringAndArray;

@RestController
public class EditorController {
	@Autowired UserService userService;
	@Autowired ArticleService articleService;
	@Autowired TagService tagService;
	@Autowired CategoryService categoryService;
	
	/**
	 * 验证是否可以写博客
	 * @return
	 */
	@GetMapping("/canYouWrite")
	public String canYouWrite() {
		Subject subject = SecurityUtils.getSubject();
		String phone = (String) subject.getPrincipal();
		if (userService.isSuperAdmin(phone)) {
			return JsonResult.success().toJSON();
		}
		return JsonResult.fail().toJSON();
	}
	
	/**
	 * 获取所有分类
	 * @return
	 */
	@GetMapping("/findCategoriesName")
	public String findCategoriesName() {
		DataMap data = categoryService.findCategoriesName();
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 获取是否有未发布的草稿文章或是修改文章
	 * @param request
	 * @return
	 */
	@GetMapping("/getDraftArticle")
	public String getDraftArticle(HttpServletRequest request) {
		String id = (String) request.getSession().getAttribute("id");
		
		//判断是否为修改文章
		if (id != null) {//为修改文章
			request.getSession().removeAttribute("id");
			Article article = articleService.findArticleById(Integer.parseInt(id));
			int lastItem = article.getArticleTags().lastIndexOf(",");
			String[] articleTags = StringAndArray.stringToArray(article.getArticleTags().substring(0, lastItem));
			DataMap data = articleService.getDraftArticle(article, articleTags, tagService.getTagsSizeByTagName(articleTags[0]));
			return JsonResult.build(data).toJSON();
		}
		//判断是否为写文章登录超时
		if (request.getSession().getAttribute("article") != null) {
			Article article = (Article) request.getSession().getAttribute("article");
			String[] articleTags = (String[]) request.getSession().getAttribute("articleTags");
			String articleGrade = (String) request.getSession().getAttribute("articleGrade");
			DataMap data = articleService.getDraftArticle(article, articleTags, Integer.parseInt(articleGrade));
			
			request.getSession().removeAttribute("article");
			request.getSession().removeAttribute("articleTags");
			request.getSession().removeAttribute("articleGrade");
			return JsonResult.build(data).toJSON();
		}
		return JsonResult.fail().toJSON();
	}
}
