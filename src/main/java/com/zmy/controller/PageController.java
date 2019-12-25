package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zmy.service.UserService;
import org.springframework.web.bind.annotation.RestController;

//专门用于显示页面的控制器
@Controller
@RequestMapping("")
//@RestController
public class PageController {
	@Autowired UserService userService;

	@PostMapping("/login")
	public String doLogin(Model model, String name, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			session.setAttribute("user", userService.findUsernameByPhone(subject.getPrincipal().toString()));
//			if (session.getAttribute("lastUrl") != null) {
//				System.out.println(session.getAttribute("lastUrl"));
//			}
			return "redirect:index";
		} catch (Exception e) {
			model.addAttribute("error", "验证失败");
			return "login";
		}
	}


}
