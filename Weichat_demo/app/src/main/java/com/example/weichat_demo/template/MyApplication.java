package com.example.weichat_demo.template;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yaochao on 2019/02/24
 */
public class MyApplication extends Application {

	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		Realm.init(this);
	}

	public static Context getContext() {
		return context;
	}
}
