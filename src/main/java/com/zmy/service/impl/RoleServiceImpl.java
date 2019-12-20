package com.zmy.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmy.mapper.RoleMapper;
import com.zmy.pojo.Role;
import com.zmy.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired RoleMapper roleMapper;
	
	@Override
	public Set<String> listRolesByUserName(String phone) {
		Set<String> result = new HashSet<>();
		List<Role> roles = roleMapper.listRolesByUserName(phone);
		for (Role role : roles) {
			result.add(role.getName());
		}
		return result;
	}

}
