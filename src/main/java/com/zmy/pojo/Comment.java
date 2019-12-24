package com.zmy.pojo;

/**
 * @ClassName:Comment
 * @Description:文章评论
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:26:51
 */
public class Comment {
	private long id;
	private long articleId;
	private long pId = 0;
	private int answererId;
	private int respondentId;
	private String commentDate;
	private int likes = 0;
	private String commentContent;
	private int isRead = 1;
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
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public Comment(long id, long articleId, long pId, int answererId, int respondentId, String commentDate, int likes,
			String commentContent, int isRead) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.pId = pId;
		this.answererId = answererId;
		this.respondentId = respondentId;
		this.commentDate = commentDate;
		this.likes = likes;
		this.commentContent = commentContent;
		this.isRead = isRead;
	}
	public Comment() {
		super();
	}
}
