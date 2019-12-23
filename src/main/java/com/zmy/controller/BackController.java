package com.zmy.controller;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BackController {
	private static final String SLASH_SYMBOL = "/";
	
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
}
