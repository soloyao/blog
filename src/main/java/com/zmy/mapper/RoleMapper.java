package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.Role;

@Mapper
public interface RoleMapper {
	List<Role> listRolesByUserName(String phone);
}
