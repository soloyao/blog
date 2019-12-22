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

}
