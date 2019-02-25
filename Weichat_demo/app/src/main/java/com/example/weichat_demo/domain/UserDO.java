package com.example.weichat_demo.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by yaochao on 2019/02/24
 */
public class UserDO extends RealmObject {

	@PrimaryKey
	private int id;

	@Required
	private String account;

	@Required
	private String password;

	private byte[] iconbytes;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getIconbytes() {
		return iconbytes;
	}

	public void setIconbytes(byte[] iconbytes) {
		this.iconbytes = iconbytes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
