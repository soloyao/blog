package com.zmy.service;

import org.springframework.stereotype.Service;

import com.zmy.util.DataMap;

@Service
public interface LeaveMessageService {
	DataMap findFiveNewLeaveMessage(int rows, int pageNum);
	int leaveMessageNum();
}
