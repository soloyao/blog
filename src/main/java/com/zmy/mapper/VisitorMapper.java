package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorMapper {
	long getVisitorNumByPageName(String pageName);
	void save(String pageName);
}
