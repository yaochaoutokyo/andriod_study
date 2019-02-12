package com.example.weichat_demo.entity;

/**
 * Created by yaochao on 2019/02/05
 */
public class SpinnerFriend {
	private int iconId;

	private String name;

	public SpinnerFriend() {
	}

	public SpinnerFriend(int iconId, String name) {
		this.iconId = iconId;
		this.name = name;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
