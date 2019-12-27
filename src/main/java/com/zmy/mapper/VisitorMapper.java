package com.zmy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorMapper {
	long getVisitorNumByPageName(String pageName);
}
