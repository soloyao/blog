package com.zmy.pojo;

/**
 * @ClassName:LeaveMessageLikesRecord
 * @Description:留言中点赞
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:25:41
 */
public class LeaveMessageLikesRecord {
	private long id;
	private String pageName;
	private int pId;
	private int likerId;
	private String likeDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public int getLikerId() {
		return likerId;
	}
	public void setLikerId(int likerId) {
		this.likerId = likerId;
	}
	public String getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}
	public LeaveMessageLikesRecord(long id, String pageName, int pId, int likerId, String likeDate) {
		super();
		this.id = id;
		this.pageName = pageName;
		this.pId = pId;
		this.likerId = likerId;
		this.likeDate = likeDate;
	}
	public LeaveMessageLikesRecord() {
		super();
	}
}
