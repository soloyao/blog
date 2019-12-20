package com.zmy.pojo;

import java.util.Date;

/**
 * @ClassName:Reward
 * @Description:募捐
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:25:00
 */
public class Reward {
	private int id;
	private String fundRaiser;
	private String fundRaisingSources;
	private String fundRaisingPlace;
	private float rewardMoney;
	private String remarks;
	private Date rewardDate;
	private String rewardUrl;
	public Reward(int id, String fundRaiser, String fundRaisingSources, String fundRaisingPlace, float rewardMoney,
			String remarks, Date rewardDate, String rewardUrl) {
		super();
		this.id = id;
		this.fundRaiser = fundRaiser;
		this.fundRaisingSources = fundRaisingSources;
		this.fundRaisingPlace = fundRaisingPlace;
		this.rewardMoney = rewardMoney;
		this.remarks = remarks;
		this.rewardDate = rewardDate;
		this.rewardUrl = rewardUrl;
	}
	public Reward() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFundRaiser() {
		return fundRaiser;
	}
	public void setFundRaiser(String fundRaiser) {
		this.fundRaiser = fundRaiser;
	}
	public String getFundRaisingSources() {
		return fundRaisingSources;
	}
	public void setFundRaisingSources(String fundRaisingSources) {
		this.fundRaisingSources = fundRaisingSources;
	}
	public String getFundRaisingPlace() {
		return fundRaisingPlace;
	}
	public void setFundRaisingPlace(String fundRaisingPlace) {
		this.fundRaisingPlace = fundRaisingPlace;
	}
	public float getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(float rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getRewardDate() {
		return rewardDate;
	}
	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}
	public String getRewardUrl() {
		return rewardUrl;
	}
	public void setRewardUrl(String rewardUrl) {
		this.rewardUrl = rewardUrl;
	}
}
