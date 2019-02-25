package com.example.weichat_demo.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yaochao on 2019/02/21
 */
public class FriendDO extends RealmObject {

	@PrimaryKey
	private int id;

	private String name;

	private String says;

	private byte[] iconBytes;

	private String phoneNumber;

	private String email;

	private Integer bestFriendId;

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

	public byte[] getIconBytes() {
		return iconBytes;
	}

	public void setIconBytes(byte[] iconBytes) {
		this.iconBytes = iconBytes;
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
