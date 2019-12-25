package com.zmy.service;

import com.zmy.pojo.LeaveMessageLikesRecord;

public interface LeaveMessageLikesRecordService {
	boolean isLiked(String pageName, int pId, int likeId);
	void insertLeaveMessageLikesRecord(LeaveMessageLikesRecord leaveMessageLikesRecord);
}
