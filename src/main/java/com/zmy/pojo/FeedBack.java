package com.zmy.pojo;

/**
 * @ClassName:FeedBack
 * @Description:反馈
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:26:18
 */
public class FeedBack {
	private int id;
	private String feedbackContent;
	private String contactInfo;
	private int personId;
	private String feedbackDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	public FeedBack(int id, String feedbackContent, String contactInfo, int personId, String feedbackDate) {
		super();
		this.id = id;
		this.feedbackContent = feedbackContent;
		this.contactInfo = contactInfo;
		this.personId = personId;
		this.feedbackDate = feedbackDate;
	}
	public FeedBack() {
		super();
	}
}
