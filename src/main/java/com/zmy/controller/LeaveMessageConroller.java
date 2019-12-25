package com.zmy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.component.JavaScriptCheck;
import com.zmy.constant.CodeType;
import com.zmy.pojo.LeaveMessage;
import com.zmy.pojo.LeaveMessageLikesRecord;
import com.zmy.service.LeaveMessageLikesRecordService;
import com.zmy.service.LeaveMessageService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;
import com.zmy.util.TimeUtil;

/**
 * @ClassName:LeaveMessageConroller
 * @Description:留言
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月23日 上午10:38:33
 */
@RestController
public class LeaveMessageConroller {
	@Autowired LeaveMessageService leaveMessageService;
	@Autowired UserService userService;
	@Autowired LeaveMessageLikesRecordService leaveMessageLikesRecordService;
	
	/**
	 * 获得当前页的留言
	 * @param pageName
	 * @return
	 */
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
	
	/**
	 * 发表留言
	 * @param leaveMessageContent
	 * @param pageName
	 * @return
	 */
	@PostMapping("/publishLeaveMessage")
	public String publishLeaveMessage(@RequestParam("leaveMessageContent") String leaveMessageContent,
			@RequestParam("pageName") String pageName) {
		Subject subject = SecurityUtils.getSubject();
		String phone = (String) subject.getPrincipal();
		leaveMessageService.publishLeaveMessage(leaveMessageContent, pageName, phone);
		DataMap data = leaveMessageService.findAllLeaveMessage(pageName, 0, phone);
		return JsonResult.build(data).toJSON();
	}
	
	/**
	 * 发布留言中的评论
	 * @param leaveMessage
	 * @param parentId
	 * @param respondent
	 * @return
	 */
	@PostMapping("/publishLeaveMessageReply")
	public String publishLeaveMessageReply(LeaveMessage leaveMessage,
			@RequestParam("parentId") String parentId,
			@RequestParam("respondent") String respondent) {
		Subject subject = SecurityUtils.getSubject();
		String phone = (String) subject.getPrincipal();
		leaveMessage.setAnswererId(userService.findIdByPhone(phone));
		leaveMessage.setpId(Integer.parseInt(parentId.substring(1)));
		leaveMessage.setLeaveMessageContent(JavaScriptCheck.javaScriptCheck(leaveMessage.getLeaveMessageContent()));
		String commentContent = leaveMessage.getLeaveMessageContent();
		if ('@' == commentContent.charAt(0)) {
			leaveMessage.setLeaveMessageContent(commentContent.substring(respondent.length() + 1).trim());
		} else {
			leaveMessage.setLeaveMessageContent(commentContent.trim());
		}
		
		if (StringUtils.isEmpty(leaveMessage.getLeaveMessageContent())) {
			return JsonResult.fail(CodeType.COMMENT_BLANK).toJSON();
		}
		
		leaveMessageService.publishLeaveMessageReply(leaveMessage, respondent);
		
		DataMap data = leaveMessageService.leaveMessageNewReply(leaveMessage, userService.findUsernameByPhone(phone), respondent);
        return JsonResult.build(data).toJSON();
	}

	@GetMapping("/addLeaveMessageLike")
	public String addLeaveMessageLike(@RequestParam("pageName") String pageName,
			@RequestParam("respondentId") String respondentId) {
		Subject subject = SecurityUtils.getSubject();
		String phone = (String) subject.getPrincipal();
		int userId = userService.findIdByPhone(phone);
		LeaveMessageLikesRecord leaveMessageLikesRecord = new LeaveMessageLikesRecord(pageName, Integer.parseInt(respondentId.substring(1)), userId, TimeUtil.getFormatDateForFive());
		
		if (leaveMessageLikesRecordService.isLiked(leaveMessageLikesRecord.getPageName(), leaveMessageLikesRecord.getpId(), userId)) {
			return JsonResult.fail(CodeType.MESSAGE_HAS_THUMBS_UP).toJSON();
		}
		
		DataMap data = leaveMessageService.updateLikeByPageNameAndId(pageName, leaveMessageLikesRecord.getpId());
		leaveMessageLikesRecordService.insertLeaveMessageLikesRecord(leaveMessageLikesRecord);
		return JsonResult.build(data).toJSON();
	}
}
