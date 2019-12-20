package com.zmy.pojo;

/**
 * @ClassName:PrivateWord
 * @Description:悄悄话
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:25:30
 */
public class PrivateWord {
	private int id;
	private String privateWord;
	private int publisherId;
	private int replierId;
	private String replyContent;
	private String publisherDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrivateWord() {
		return privateWord;
	}
	public void setPrivateWord(String privateWord) {
		this.privateWord = privateWord;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public int getReplierId() {
		return replierId;
	}
	public void setReplierId(int replierId) {
		this.replierId = replierId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getPublisherDate() {
		return publisherDate;
	}
	public void setPublisherDate(String publisherDate) {
		this.publisherDate = publisherDate;
	}
	public PrivateWord(int id, String privateWord, int publisherId, int replierId, String replyContent,
			String publisherDate) {
		super();
		this.id = id;
		this.privateWord = privateWord;
		this.publisherId = publisherId;
		this.replierId = replierId;
		this.replyContent = replyContent;
		this.publisherDate = publisherDate;
	}
	public PrivateWord() {
		super();
	}
}
