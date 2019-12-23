package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.LeaveMessage;

@Mapper
public interface LeaveMessageMapper {
	List<LeaveMessage> findFiveNewLeaveWord();
	int leaveMessageNum();
	List<LeaveMessage> findAllLeaveMessage(String pageName, int pId);
	List<LeaveMessage> findLeaveMessageReplyByPageNameAndPid(String pageName, int pId);
}