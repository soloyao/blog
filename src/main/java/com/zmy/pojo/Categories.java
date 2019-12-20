package com.zmy.pojo;

/**
 * @ClassName:Categories
 * @Description:文章分类
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:27:00
 */
public class Categories {
	private int id;
	private String categoryName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Categories(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	public Categories() {
		super();
	}
}
