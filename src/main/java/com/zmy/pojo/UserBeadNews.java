package com.zmy.pojo;

/**
 * @ClassName:UserBeadNews
 * @Description:用户评论留言未读数
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:24:12
 */
public class UserBeadNews {
	private int allNewsNum;
	private int commentNum;
	private int leaveMessageNum;
	public int getAllNewsNum() {
		return allNewsNum;
	}
	public void setAllNewsNum(int allNewsNum) {
		this.allNewsNum = allNewsNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getLeaveMessageNum() {
		return leaveMessageNum;
	}
	public void setLeaveMessageNum(int leaveMessageNum) {
		this.leaveMessageNum = leaveMessageNum;
	}
	public UserBeadNews(int allNewsNum, int commentNum, int leaveMessageNum) {
		super();
		this.allNewsNum = allNewsNum;
		this.commentNum = commentNum;
		this.leaveMessageNum = leaveMessageNum;
	}
	public UserBeadNews() {
		super();
	}
}
