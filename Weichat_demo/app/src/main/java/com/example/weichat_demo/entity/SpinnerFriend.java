package com.example.weichat_demo.entity;

import android.graphics.Bitmap;

/**
 * Created by yaochao on 2019/02/05
 */
public class SpinnerFriend {
	private Bitmap iconBitmap;

	private String name;

	public SpinnerFriend() {
	}

	public SpinnerFriend(Bitmap iconBitmap, String name) {
		this.iconBitmap = iconBitmap;
		this.name = name;
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
}
