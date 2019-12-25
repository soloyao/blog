package com.zmy.pojo;

/**
 * @ClassName:LeaveMessage
 * @Description:留言
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:25:54
 */
public class LeaveMessage {
	private int id;
	private String pageName;
	private int pId = 0;
	private int answererId;
	private int respondentId;
	private String leaveMessageDate;
	private int likes = 0;
	private String leaveMessageContent;
	private int isRead = 1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getAnswererId() {
		return answererId;
	}
	public void setAnswererId(int answererId) {
		this.answererId = answererId;
	}
	public int getRespondentId() {
		return respondentId;
	}
	public void setRespondentId(int respondentId) {
		this.respondentId = respondentId;
	}
	public String getLeaveMessageDate() {
		return leaveMessageDate;
	}
	public void setLeaveMessageDate(String leaveMessageDate) {
		this.leaveMessageDate = leaveMessageDate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getLeaveMessageContent() {
		return leaveMessageContent;
	}
	public void setLeaveMessageContent(String leaveMessageContent) {
		this.leaveMessageContent = leaveMessageContent;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public LeaveMessage(String pageName, int answererId, int respondentId, String leaveMessageDate, String leaveMessageContent) {
		super();
		this.pageName = pageName;
		this.answererId = answererId;
		this.respondentId = respondentId;
		this.leaveMessageDate = leaveMessageDate;
		this.leaveMessageContent = leaveMessageContent;
	}
	public LeaveMessage() {
		super();
	}
}
