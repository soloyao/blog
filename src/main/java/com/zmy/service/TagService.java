package com.zmy.service;

import com.zmy.util.DataMap;

public interface TagService {
	DataMap findTagsCloud();
	int countTagsNum();
	int getTagsSizeByTagName(String tagName);
}
