package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//专门用于显示页面的控制器
@Controller
@RequestMapping("")
//@RestController
public class PageController {

	@PostMapping("/login")
	public String doLogin(Model model, String name, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			return "redirect:index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "验证失败");
			return "login";
		}
	}


}
