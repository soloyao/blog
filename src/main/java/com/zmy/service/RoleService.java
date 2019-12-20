package com.zmy.service;

import java.util.Set;

public interface RoleService {
	Set<String> listRolesByUserName(String phone);
}
