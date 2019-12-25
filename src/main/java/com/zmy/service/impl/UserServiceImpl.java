package com.zmy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmy.mapper.UserMapper;
import com.zmy.pojo.User;
import com.zmy.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired UserMapper userMapper;
	
	@Override
	public User getByName(String phone) {
		return userMapper.getByName(phone);
	}

	@Override
	public String findUsernameById(int id) {
		return userMapper.findUsernameById(id);
	}

	@Override
	public String getHeadPortraitUrl(int id) {
		return userMapper.getHeadPortraitUrl(id);
	}

	@Override
	public int findIdByPhone(String phone) {
		return userMapper.findIdByPhone(phone);
	}

	@Override
	public int findIdByUsername(String username) {
		return userMapper.findIdByUsername(username);
	}

	@Override
	public String findUsernameByPhone(String phone) {
		return userMapper.findUsernameByPhone(phone);
	}

}
