package com.example.weichat_demo.domain;

/**
 * Created by yaochao on 2019/02/21
 */
public class FriendDO {

	private String name;

	private String says;

	private int iconId;

	private Integer phoneNumber;

	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSays() {
		return says;
	}

	public void setSays(String says) {
		this.says = says;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
