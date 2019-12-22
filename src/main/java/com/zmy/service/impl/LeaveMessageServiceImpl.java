package com.zmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.mapper.LeaveMessageMapper;
import com.zmy.pojo.LeaveMessage;
import com.zmy.service.LeaveMessageService;
import com.zmy.service.UserService;
import com.zmy.util.DataMap;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {
	@Autowired LeaveMessageMapper leaveMessageMapper;
	@Autowired UserService userService;
	
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

}
