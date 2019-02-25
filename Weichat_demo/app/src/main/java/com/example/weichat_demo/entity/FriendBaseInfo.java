package com.example.weichat_demo.entity;

import android.graphics.Bitmap;

/**
 * Created by yaochao on 2019/02/03
 */
public class FriendBaseInfo {

	private Bitmap iconBitmap;

	private String name;

	private String says;

	public FriendBaseInfo() {
	}

	public FriendBaseInfo(Bitmap iconBitmap, String name, String says) {
		this.iconBitmap = iconBitmap;
		this.name = name;
		this.says = says;
	}

	public Bitmap getIconBitmap() {
		return iconBitmap;
	}

	public void setIconBitmap(Bitmap iconBitmap) {
		this.iconBitmap = iconBitmap;
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
