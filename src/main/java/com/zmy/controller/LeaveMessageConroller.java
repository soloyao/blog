package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.service.LeaveMessageService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

/**
 * @ClassName:LeaveMessageConroller
 * @Description:留言
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月23日 上午10:38:33
 */
@RestController
public class LeaveMessageConroller {
	@Autowired LeaveMessageService leaveMessageService;
	
	@GetMapping("/getPageLeaveMessage")
	public String getPageLeaveMessage(@RequestParam("pageName") String pageName) {
		Subject subject = SecurityUtils.getSubject();
		String phone = null;
		if (subject.isAuthenticated()) {
			phone = (String) subject.getPrincipal();
		}
		DataMap data = leaveMessageService.findAllLeaveMessage(pageName, 0, phone);
		return JsonResult.build(data).toJSON();
	}
}
