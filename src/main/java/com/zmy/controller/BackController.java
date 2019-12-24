package com.zmy.controller;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zmy.service.ArticleService;

@RestController
public class BackController {
	private static final String SLASH_SYMBOL = "/";
	
	@Autowired ArticleService articleService;
	
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
	
	@GetMapping("/superadmin")
	public ModelAndView superadmin() {
		ModelAndView mav = new ModelAndView("superadmin");
		return mav;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@GetMapping("/categories")
	public ModelAndView categories() {
		ModelAndView mav = new ModelAndView("categories");
		return mav;
	}
	
	@GetMapping("/archives")
	public ModelAndView archives() {
		ModelAndView mav = new ModelAndView("archives");
		return mav;
	}
	
	@GetMapping("/tags")
	public ModelAndView tags() {
		ModelAndView mav = new ModelAndView("tags");
		return mav;
	}
	
	@GetMapping("/article/{articleId}")
	public ModelAndView show(@PathVariable("articleId") long articleId) {
		Map<String, String> articleMap = articleService.findArticleTitleByArticleId(articleId);
		ModelAndView mav = new ModelAndView("show");
		if (articleMap.get("articleTitle") != null) {
			mav.addObject("articleTitle", articleMap.get("articleTitle"));
		}
		return mav;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
}
