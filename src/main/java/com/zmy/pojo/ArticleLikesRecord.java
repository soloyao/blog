package com.zmy.pojo;

/**
 * @ClassName:ArticleLikesRecord
 * @Description:文章点赞记录
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:22:38
 */
public class ArticleLikesRecord {
	private long id;
	private int likerId;
	private long articleId;
	private String likeDate;
	private int isRead = 1;
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public ArticleLikesRecord(long id, int likerId, String likeDate, int isRead) {
		super();
		this.id = id;
		this.likerId = likerId;
		this.likeDate = likeDate;
		this.isRead = isRead;
	}
	public ArticleLikesRecord() {
		super();
	}
	public ArticleLikesRecord(long articleId, int likerId, String likeDate) {
        this.articleId = articleId;
        this.likerId = likerId;
        this.likeDate = likeDate;
    }
}
