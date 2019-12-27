package com.zmy.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmy.mapper.VisitorMapper;
import com.zmy.service.VisitorService;
import com.zmy.util.DataMap;

@Service
public class VisitorServiceImpl implements VisitorService {
	@Autowired VisitorMapper visitorMapper;
	
	@Override
	public DataMap addVisitorNumByPageName(String pageName, HttpServletRequest request) {
		return null;
	}

	@Override
	public long getNumByPageName(String pageName) {
		return visitorMapper.getVisitorNumByPageName(pageName);
	}

}
