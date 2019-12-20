package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.User;

@Mapper
public interface UserMapper {
	User getByName(String phone);
}
