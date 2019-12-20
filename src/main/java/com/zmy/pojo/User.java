package com.zmy.pojo;

import java.util.List;

/**
 * @ClassName:User
 * @Description:用户实体类
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:24:34
 */
public class User {
	private int id;
	private String phone;
	private String username;
	private String password;
	private String gender;
	private String trueName;
	private String birthday;
	private String personalBrief;
	private String email;
	private String recentlyLanded;
	private String avatarImgUrl;
	private List<Role> roles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPersonalBrief() {
		return personalBrief;
	}
	public void setPersonalBrief(String personalBrief) {
		this.personalBrief = personalBrief;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRecentlyLanded() {
		return recentlyLanded;
	}
	public void setRecentlyLanded(String recentlyLanded) {
		this.recentlyLanded = recentlyLanded;
	}
	public String getAvatarImgUrl() {
		return avatarImgUrl;
	}
	public void setAvatarImgUrl(String avatarImgUrl) {
		this.avatarImgUrl = avatarImgUrl;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User(int id, String phone, String username, String password, String gender, String trueName, String birthday,
			String personalBrief, String email, String recentlyLanded, String avatarImgUrl, List<Role> roles) {
		super();
		this.id = id;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.trueName = trueName;
		this.birthday = birthday;
		this.personalBrief = personalBrief;
		this.email = email;
		this.recentlyLanded = recentlyLanded;
		this.avatarImgUrl = avatarImgUrl;
		this.roles = roles;
	}
	public User() {
		super();
	}
}
