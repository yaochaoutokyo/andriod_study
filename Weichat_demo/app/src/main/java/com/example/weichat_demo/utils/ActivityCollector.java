package com.example.weichat_demo.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by yaochao on 2019/02/09
 */
public class ActivityCollector {

	private static ArrayList<AppCompatActivity> activityArrayList = new ArrayList<>();

	public static void add(AppCompatActivity activity) {
		activityArrayList.add(activity);
	}

	public static void remove(AppCompatActivity activity) {
		activityArrayList.remove(activity);
	}

	public static void finishAll() {
		for (AppCompatActivity activity : activityArrayList) {
			if (! activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	// 彻底退出app
	public static void appExit(Context context) {
		try {
			ActivityCollector.finishAll();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception ignored) {}
	}
}
