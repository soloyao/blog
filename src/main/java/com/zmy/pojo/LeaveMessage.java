package com.zmy.pojo;

/**
 * @ClassName:LeaveMessage
 * @Description:留言
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:25:54
 */
public class LeaveMessage {
	private int id;
	private String blogger;
	private String url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogger() {
		return blogger;
	}
	public void setBlogger(String blogger) {
		this.blogger = blogger;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LeaveMessage(int id, String blogger, String url) {
		super();
		this.id = id;
		this.blogger = blogger;
		this.url = url;
	}
	public LeaveMessage() {
		super();
	}
}
