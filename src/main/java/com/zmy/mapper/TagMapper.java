package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.Tag;

@Mapper
public interface TagMapper {
	List<Tag> findTagsCloud();
	int countTagsNum();
}
