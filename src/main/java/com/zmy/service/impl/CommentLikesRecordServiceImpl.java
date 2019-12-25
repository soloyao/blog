package com.zmy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmy.mapper.CommentLikesMapper;
import com.zmy.pojo.CommentLikesRecord;
import com.zmy.service.CommentLikesRecordService;
import com.zmy.service.UserService;

@Service
public class CommentLikesRecordServiceImpl implements CommentLikesRecordService {
	@Autowired CommentLikesMapper commentLikesMapper;
	@Autowired UserService userSerivice;
	
	@Override
	public boolean isLiked(long articleId, long pId, String phone) {
		return commentLikesMapper.isLiked(articleId, pId, userSerivice.findIdByPhone(phone)) != null;
	}

	@Override
	public void insertCommentLikesRecord(CommentLikesRecord commentLikesRecord) {
		commentLikesMapper.save(commentLikesRecord);
	}

}
