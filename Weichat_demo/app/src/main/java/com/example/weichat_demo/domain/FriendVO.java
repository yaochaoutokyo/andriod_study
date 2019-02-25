package com.example.weichat_demo.domain;

import android.graphics.Bitmap;

import com.example.weichat_demo.utils.BitmapTransferUtil;

/**
 * Created by yaochao on 2019/02/25
 */
public class FriendVO {
	private int id;

	private String name;

	private String says;

	private Bitmap icon;

	private String phoneNumber;

	private String email;

	private Integer bestFriendId;

	public FriendVO(FriendDO friendDO) {
		this.id = friendDO.getId();
		this.name = friendDO.getName();
		this.says = friendDO.getSays();
		this.icon = BitmapTransferUtil.BytesToBitmap(friendDO.getIconBytes());
		this.phoneNumber = friendDO.getPhoneNumber();
		this.email = friendDO.getEmail();
		this.bestFriendId = friendDO.getBestFriendId();
	}

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

	public String getSays() {
		return says;
	}

	public void setSays(String says) {
		this.says = says;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBestFriendId() {
		return bestFriendId;
	}

	public void setBestFriendId(Integer bestFriendId) {
		this.bestFriendId = bestFriendId;
	}

}
