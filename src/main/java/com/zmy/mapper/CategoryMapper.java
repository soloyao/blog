package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
	List<String> findCategoriesName();
}
