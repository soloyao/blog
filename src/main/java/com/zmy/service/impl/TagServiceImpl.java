package com.zmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zmy.constant.CodeType;
import com.zmy.mapper.TagMapper;
import com.zmy.pojo.Tag;
import com.zmy.service.TagService;
import com.zmy.util.DataMap;

@Service
public class TagServiceImpl implements TagService {
	@Autowired TagMapper tagMapper;
	
	@Override
	public DataMap findTagsCloud() {
		List<Tag> tags = tagMapper.findTagsCloud();
		JSONObject json = new JSONObject();
		json.put("result", tags);
		json.put("tagsNum", tags.size());
		return DataMap.success(CodeType.FIND_TAGS_CLOUD).setData(json);
	}

	@Override
	public int countTagsNum() {
		return tagMapper.countTagsNum();
	}

	@Override
	public int getTagsSizeByTagName(String tagName) {
		return tagMapper.getTagsSizeByTagName(tagName);
	}

	@Override
	public void addTags(String[] tags, int tagSize) {
		for (String tag : tags) {
			if (tagMapper.findIsExistByTagName(tag) == 0) {
				Tag t = new Tag(tag, tagSize);
				tagMapper.save(t);
			}
		}
	}

}
