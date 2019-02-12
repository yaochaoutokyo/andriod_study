package com.example.weichat_demo.entity;

/**
 * Created by yaochao on 2019/02/03
 */
public class Friend {

	private int iconId;

	private String name;

	private String says;

	public Friend() {
	}

	public Friend(int iconId, String name, String says) {
		this.iconId = iconId;
		this.name = name;
		this.says = says;
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

	public String getSays() {
		return says;
	}

	public void setSays(String says) {
		this.says = says;
	}
}
