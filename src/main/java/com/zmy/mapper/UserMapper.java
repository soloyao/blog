package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.User;

@Mapper
public interface UserMapper {
	User getByName(String phone);
	String findUsernameById(int id);
	String getHeadPortraitUrl(int id);
	int findIdByPhone(String phone);
	int findIdByUsername(String username);
	String findUsernameByPhone(String phone);
}
