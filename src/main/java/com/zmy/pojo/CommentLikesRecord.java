package com.zmy.pojo;

/**
 * @ClassName:CommentLikesRecord
 * @Description:文章评论点赞记录
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:26:41
 */
public class CommentLikesRecord {
	private long id;
	private long articleId;
	private long pId;
	private int likerId;
	private String likeDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
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
	public CommentLikesRecord(long id, long articleId, long pId, int likerId, String likeDate) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.pId = pId;
		this.likerId = likerId;
		this.likeDate = likeDate;
	}
	public CommentLikesRecord(long articleId, int pId, int likerId, String likeDate) {
        this.articleId = articleId;
        this.pId = pId;
        this.likerId = likerId;
        this.likeDate = likeDate;
    }
	public CommentLikesRecord() {
		super();
	}
}
