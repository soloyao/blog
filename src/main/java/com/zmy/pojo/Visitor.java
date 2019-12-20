package com.zmy.pojo;

/**
 * @ClassName:Visitor
 * @Description:访客
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:21:41
 */
public class Visitor {
	private int id;
	private long visitorNum;
	private String pageNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getVisitorNum() {
		return visitorNum;
	}
	public void setVisitorNum(long visitorNum) {
		this.visitorNum = visitorNum;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public Visitor(int id, long visitorNum, String pageNum) {
		super();
		this.id = id;
		this.visitorNum = visitorNum;
		this.pageNum = pageNum;
	}
	public Visitor() {
		super();
	}
}
