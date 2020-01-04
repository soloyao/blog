package com.zmy.service;

import javax.servlet.http.HttpServletRequest;

import com.zmy.util.DataMap;

public interface VisitorService {
	DataMap addVisitorNumByPageName(String pageName, HttpServletRequest request);
	long getNumByPageName(String pageName);
	void insertVisitorArticlePage(String pageName);
}
