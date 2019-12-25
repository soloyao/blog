package com.zmy.service;

import org.springframework.stereotype.Service;

import com.zmy.pojo.LeaveMessage;
import com.zmy.util.DataMap;

@Service
public interface LeaveMessageService {
	DataMap findFiveNewLeaveMessage(int rows, int pageNum);
	int leaveMessageNum();
	DataMap findAllLeaveMessage(String pageName, int pId, String phone);
	void publishLeaveMessage(String leaveMessageContent, String pageName, String phone);
	void publishLeaveMessageReply(LeaveMessage leaveMessage, String respondent);
	DataMap leaveMessageNewReply(LeaveMessage leaveMessage, String answerer, String respondent);
	DataMap updateLikeByPageNameAndId(String pageName, int id);
}
