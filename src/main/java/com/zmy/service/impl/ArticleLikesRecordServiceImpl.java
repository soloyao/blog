package com.zmy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmy.mapper.ArticleLikesMapper;
import com.zmy.pojo.ArticleLikesRecord;
import com.zmy.service.ArticleLikesRecordService;
import com.zmy.service.UserService;

@Service
public class ArticleLikesRecordServiceImpl implements ArticleLikesRecordService {
	@Autowired ArticleLikesMapper articleLikesMapper;
	@Autowired UserService userService;
	
	@Override
	public boolean isLiked(long articleId, String phone) {
		return articleLikesMapper.isLiked(articleId, userService.findIdByPhone(phone)) != null;
	}

	@Override
	public void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord) {
		articleLikesMapper.save(articleLikesRecord);
	}

}
