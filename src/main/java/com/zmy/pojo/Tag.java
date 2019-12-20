package com.zmy.pojo;

/**
 * @ClassName:Tag
 * @Description:标签
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:24:43
 */
public class Tag {
	private int id;
	private String tagName;
	private int tagSize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getTagSize() {
		return tagSize;
	}
	public void setTagSize(int tagSize) {
		this.tagSize = tagSize;
	}
	public Tag(int id, String tagName, int tagSize) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.tagSize = tagSize;
	}
	public Tag() {
		super();
	}
}
