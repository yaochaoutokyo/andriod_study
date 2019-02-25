package com.example.weichat_demo.entity;

/**
 * Created by yaochao on 2019/02/23
 */
public class AppInfo {

	private String id;

	private String name;

	private String version;

	public AppInfo() {
	}

	public AppInfo(String id, String name, String version) {
		this.id = id;
		this.name = name;
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "id: " + id + "; name: " + name + "; version: " + version;
	}
}
