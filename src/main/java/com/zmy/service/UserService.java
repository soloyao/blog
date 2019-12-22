package com.zmy.service;

import com.zmy.pojo.User;

public interface UserService {
	User getByName(String phone);
	String findUsernameById(int id);
}
