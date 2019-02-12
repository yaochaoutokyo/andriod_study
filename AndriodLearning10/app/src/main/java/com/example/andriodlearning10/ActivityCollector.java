package com.example.andriodlearning10;

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
}
