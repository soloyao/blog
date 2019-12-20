package com.zmy.pojo;

/**
 * @ClassName:FrendLink
 * @Description:友情链接
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:26:06
 */
public class FriendLink {
	private int id;
	private String blogger;
	private String url;
	public FriendLink(int id, String blogger, String url) {
		super();
		this.id = id;
		this.blogger = blogger;
		this.url = url;
	}
	public FriendLink() {
		super();
	}
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
}
