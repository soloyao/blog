package com.zmy.controller;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zmy.service.ArticleService;

@RestController
public class BackController {
	private static final String SLASH_SYMBOL = "/";
	
	@Autowired ArticleService articleService;
	
	/**
	 * 跳转登录页面
	 * @param request
	 */
	@GetMapping("/toLogin")
	public void toLogin(HttpServletRequest request) {
		//保存跳转页面的url
		String lastUrl = request.getHeader("Referer");
		if (lastUrl != null) {
			try {
				URL url = new URL(lastUrl);
				if (!SLASH_SYMBOL.equals(url.getPath())) {
					request.getSession().setAttribute("lastUrl", lastUrl);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 网站管理页面
	 * @return
	 */
	@GetMapping("/superadmin")
	public ModelAndView superadmin() {
		ModelAndView mav = new ModelAndView("superadmin");
		return mav;
	}
	
	/**
	 * 写博客页面
	 * @return
	 */
	@GetMapping("/editor")
	public ModelAndView editor(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("editor");
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			request.getSession().setAttribute("id", id);
		}
		return mav;
	}
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index");
		response.setHeader("Access-Control-Allow-Origin", "*");
        //判断是否有需要回跳的url，有则将需要回跳的url保存在响应头中
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
		return mav;
	}
	
	/**
	 * 分类
	 * @return
	 */
	@GetMapping("/categories")
	public ModelAndView categories() {
		ModelAndView mav = new ModelAndView("categories");
		return mav;
	}
	
	/**
	 * 归档
	 * @return
	 */
	@GetMapping("/archives")
	public ModelAndView archives() {
		ModelAndView mav = new ModelAndView("archives");
		return mav;
	}
	
	/**
	 * 标签
	 * @return
	 */
	@GetMapping("/tags")
	public ModelAndView tags() {
		ModelAndView mav = new ModelAndView("tags");
		return mav;
	}
	
	/**
	 * 文章
	 * @param articleId
	 * @return
	 */
	@GetMapping("/article/{articleId}")
	public ModelAndView show(@PathVariable("articleId") long articleId) {
		Map<String, String> articleMap = articleService.findArticleTitleByArticleId(articleId);
		ModelAndView mav = new ModelAndView("show");
		if (articleMap.get("articleTitle") != null) {
			mav.addObject("articleTitle", articleMap.get("articleTitle"));
		}
		return mav;
	}
	
	/**
	 * 登录页面
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
}
