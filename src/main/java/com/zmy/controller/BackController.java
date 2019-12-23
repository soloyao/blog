package com.zmy.controller;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
