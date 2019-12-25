package com.zmy.service;

import com.zmy.pojo.User;

public interface UserService {
	User getByName(String phone);
	String findUsernameById(int id);
	String getHeadPortraitUrl(int id);
	int findIdByPhone(String phone);
	int findIdByUsername(String username);
	String findUsernameByPhone(String phone);
}
