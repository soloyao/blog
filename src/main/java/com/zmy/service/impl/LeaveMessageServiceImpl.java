package com.zmy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.component.JavaScriptCheck;
import com.zmy.constant.SiteOwner;
import com.zmy.mapper.LeaveMessageMapper;
import com.zmy.pojo.LeaveMessage;
import com.zmy.service.LeaveMessageLikesRecordService;
import com.zmy.service.LeaveMessageService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;
import com.zmy.util.TimeUtil;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {
	@Autowired LeaveMessageMapper leaveMessageMapper;
	@Autowired UserService userService;
	@Autowired LeaveMessageLikesRecordService leaveMessageLikesRecordService;
	
	@Override
	public DataMap findFiveNewLeaveMessage(int rows, int pageNum) {
		JSONObject returnJson = new JSONObject();
		PageHelper.startPage(pageNum, rows, "id desc");
		List<LeaveMessage> fiveLeaveWords = leaveMessageMapper.findFiveNewLeaveWord();
		PageInfo<LeaveMessage> pageInfo = new PageInfo<LeaveMessage>(fiveLeaveWords);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (LeaveMessage leaveMessage : fiveLeaveWords) {
			json = new JSONObject();
			if (leaveMessage.getpId() != 0) {
				leaveMessage.setLeaveMessageContent("@" + userService.findUsernameById(leaveMessage.getRespondentId()) + " " + leaveMessage.getLeaveMessageContent());
			}
			json.put("id", leaveMessage.getId());
			json.put("pagePath", leaveMessage.getPageName());
			json.put("answerer", userService.findUsernameById(leaveMessage.getAnswererId()));
			json.put("leaveWordDate", leaveMessage.getLeaveMessageDate().substring(0, 10));
			json.put("leaveWordContent", leaveMessage.getLeaveMessageContent());
			jsonArray.add(json);
		}
		returnJson.put("result", jsonArray);
		JSONObject pageJson = new JSONObject();
		pageJson.put("pageNum", pageInfo.getPageNum());
		pageJson.put("pageSize", pageInfo.getPageSize());
		pageJson.put("total", pageInfo.getTotal());
		pageJson.put("pages", pageInfo.getPages());
		pageJson.put("isFirstPage", pageInfo.isIsFirstPage());
		pageJson.put("isLastPage", pageInfo.isIsLastPage());
		returnJson.put("pageInfo", pageJson);
		
		return DataMap.success().setData(returnJson);
	}

	@Override
	public int leaveMessageNum() {
		return leaveMessageMapper.leaveMessageNum();
	}

	@Override
	public DataMap findAllLeaveMessage(String pageName, int pId, String phone) {
		List<LeaveMessage> leaveMessages = leaveMessageMapper.findAllLeaveMessage(pageName, pId);
		JSONObject returnJson, replyJson;
		JSONObject leaveMessageJson;
		JSONArray replyJsonArray;
		JSONArray leaveMessageJsonArray = new JSONArray();
		List<LeaveMessage> leaveMessageReplies;
		
		returnJson = new JSONObject();
		
		for (LeaveMessage leaveMessage : leaveMessages) {
			leaveMessageJson = new JSONObject();
			leaveMessageJson.put("id", leaveMessage.getId());
			leaveMessageJson.put("answerer", userService.findUsernameById(leaveMessage.getAnswererId()));
			leaveMessageJson.put("leaveMessageDate", leaveMessage.getLeaveMessageDate());
			leaveMessageJson.put("likes", leaveMessage.getLikes());
			leaveMessageJson.put("avatarImgUrl", userService.getHeadPortraitUrl(leaveMessage.getAnswererId()).trim());
			leaveMessageJson.put("leaveMessageContent", leaveMessage.getLeaveMessageContent());
			if (null == phone) {
				leaveMessageJson.put("isLiked", 0);
			} else {
				if (!leaveMessageLikesRecordService.isLiked(pageName, leaveMessage.getId(), userService.findIdByPhone(phone))) {
					leaveMessageJson.put("isLiked", 0);
				} else {
					leaveMessageJson.put("isLiked", 1);
				}
			}
			
			leaveMessageReplies = leaveMessageMapper.findLeaveMessageReplyByPageNameAndPid(pageName, leaveMessage.getId());
			replyJsonArray = new JSONArray();
			for (LeaveMessage reply : leaveMessageReplies) {
				replyJson = new JSONObject();
				replyJson.put("id", reply.getId());
				replyJson.put("answerer", userService.findUsernameById(reply.getAnswererId()));
				replyJson.put("respondent", userService.findUsernameById(reply.getRespondentId()));
				replyJson.put("leaveMessageDate", reply.getLeaveMessageDate());
				replyJson.put("leaveMessageContent", reply.getLeaveMessageContent());
				
				replyJsonArray.add(replyJson);
			}
			leaveMessageJson.put("replies", replyJsonArray);
			leaveMessageJsonArray.add(leaveMessageJson);
		}
		returnJson.put("result", leaveMessageJsonArray);
		
		return DataMap.success().setData(returnJson);
	}

	@Override
	public void publishLeaveMessage(String leaveMessageContent, String pageName, String phone) {
		String nowStr = TimeUtil.getFormatDateForFive();
		leaveMessageContent = JavaScriptCheck.javaScriptCheck(leaveMessageContent);
		LeaveMessage leaveMessage = new LeaveMessage(pageName, userService.findIdByPhone(phone), userService.findIdByPhone(SiteOwner.SITE_OWNER), nowStr, leaveMessageContent);
		
		if (leaveMessage.getAnswererId() == leaveMessage.getRespondentId()) {
			leaveMessage.setIsRead(0);
		}
		leaveMessageMapper.save(leaveMessage);
	}

	@Override
	public void publishLeaveMessageReply(LeaveMessage leaveMessage, String respondent) {
		String nowStr = TimeUtil.getFormatDateForFive();
		leaveMessage.setLeaveMessageDate(nowStr);
		leaveMessage.setRespondentId(userService.findIdByUsername(respondent));
		if (leaveMessage.getAnswererId() == leaveMessage.getRespondentId()) {
			leaveMessage.setIsRead(0);
		}
		leaveMessageMapper.save(leaveMessage);
	}

	@Override
	public DataMap leaveMessageNewReply(LeaveMessage leaveMessage, String answerer, String respondent) {
		Map<String, Object> dataMap = new HashMap<>(4);
        dataMap.put("answerer",answerer);
        dataMap.put("respondent",respondent);
        dataMap.put("leaveMessageContent",leaveMessage.getLeaveMessageContent());
        dataMap.put("leaveMessageDate",leaveMessage.getLeaveMessageDate());
        return DataMap.success().setData(dataMap);
	}

	@Override
	public DataMap updateLikeByPageNameAndId(String pageName, int id) {
		leaveMessageMapper.updateLikeByPageNameAndId(pageName, id);
		int likes = leaveMessageMapper.findLikesByPageNameAndId(pageName, id);
		return DataMap.success().setData(likes);
	}

}
