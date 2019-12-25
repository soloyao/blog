package com.zmy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmy.mapper.LeaveMessageLikesRecordMapper;
import com.zmy.pojo.LeaveMessageLikesRecord;
import com.zmy.service.LeaveMessageLikesRecordService;

@Service
public class LeaveMessageLikesRecordServiceImpl implements LeaveMessageLikesRecordService {
	@Autowired LeaveMessageLikesRecordMapper leaveMessageLikesRecordMapper;
	
	@Override
	public boolean isLiked(String pageName, int pId, int likeId) {
		return leaveMessageLikesRecordMapper.isLiked(pageName, pId, likeId) != null;
	}

	@Override
	public void insertLeaveMessageLikesRecord(LeaveMessageLikesRecord leaveMessageLikesRecord) {
		leaveMessageLikesRecordMapper.save(leaveMessageLikesRecord);
	}

}
