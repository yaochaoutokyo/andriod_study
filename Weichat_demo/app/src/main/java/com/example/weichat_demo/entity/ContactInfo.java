package com.example.weichat_demo.entity;

/**
 * Created by yaochao on 2019/02/21
 */
public class ContactInfo {

	private int contactIconId;

	private String contactName;

	private String contactPhone;

	private String contactEmail;

	public ContactInfo(int contactIconId, String contactName, String contactPhone, String contactEmail) {
		this.contactIconId = contactIconId;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}

	public int getContactIconId() {
		return contactIconId;
	}

	public void setContactIconId(int contactIconId) {
		this.contactIconId = contactIconId;
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
