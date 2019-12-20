package com.zmy.pojo;

/**
 * @ClassName:Role
 * @Description:权限
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:24:53
 */
public class Role {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Role() {
		super();
	}
}
