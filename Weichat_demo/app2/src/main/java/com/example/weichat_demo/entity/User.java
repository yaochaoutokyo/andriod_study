package com.example.weichat_demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaochao on 2019/02/09
 */
public class User implements Parcelable {

	private String account;

	private String passwrod;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.account);
		dest.writeString(this.passwrod);
	}

	public User() {
	}

	public User(String account, String passwrod) {
		this.account = account;
		this.passwrod = passwrod;
	}

	protected User(Parcel in) {
		this.account = in.readString();
		this.passwrod = in.readString();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}
