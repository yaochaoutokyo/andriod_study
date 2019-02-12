package com.example.andriodlearning12;

import android.app.Application;

/**
 * Created by yaochao on 2019/02/10
 */
public class MyApp extends Application {

	private String name;

	private static MyApp myApp;

	public static MyApp getInstance() {
		return myApp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		myApp = this;
	}
}
