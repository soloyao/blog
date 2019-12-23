package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.LeaveMessageLikesRecord;

@Mapper
public interface LeaveMessageLikesRecordMapper {
	LeaveMessageLikesRecord isLiked(String pageName, int pId, int likerId);
}
