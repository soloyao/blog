package com.zmy.pojo;

import java.util.Date;

/**
 * @ClassName:DailySpeech
 * @Description:日常
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:26:26
 */
public class DailySpeech {
	private String content;
	private String mood;
	private String picsUrl;
	private Date publishDate;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getPicsUrl() {
		return picsUrl;
	}
	public void setPicsUrl(String picsUrl) {
		this.picsUrl = picsUrl;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public DailySpeech(String content, String mood, String picsUrl, Date publishDate) {
		super();
		this.content = content;
		this.mood = mood;
		this.picsUrl = picsUrl;
		this.publishDate = publishDate;
	}
	public DailySpeech() {
		super();
	}
}
