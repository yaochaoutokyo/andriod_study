package com.example.weichat_demo.entity;

import android.graphics.Bitmap;

/**
 * Created by yaochao on 2019/02/21
 */
public class ContactInfo {

	private Bitmap contactBitmap;

	private String contactName;

	private String contactPhone;

	private String contactEmail;

	public ContactInfo() {
	}

	public Bitmap getContactBitmap() {
		return contactBitmap;
	}

	public void setContactBitmap(Bitmap contactBitmap) {
		this.contactBitmap = contactBitmap;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
}
