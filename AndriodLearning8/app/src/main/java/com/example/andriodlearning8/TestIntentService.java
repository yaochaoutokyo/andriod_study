package com.example.andriodlearning8;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestIntentService extends IntentService {

	public TestIntentService() {
		super("TestIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String action = intent.getExtras().getString("param");
		switch (action) {
			case "ac1":
				Log.i("TestIntentService", "onHandleIntent()-ac1");
				break;
			case "ac2":
				Log.i("TestIntentService", "onHandleIntent()-ac2");
				break;
			case "ac3":
				Log.i("TestIntentService", "onHandleIntent()-ac3");
				break;
			default:
				break;
		}

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setIntentRedelivery(boolean enabled) {
		super.setIntentRedelivery(enabled);
		Log.i("TestIntentService", "setIntentRedelivery()");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("TestIntentService", "onCreate()");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("TestIntentService", "onStart()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("TestIntentService", "onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("TestIntentService", "onDestroy()");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("TestIntentService", "onBind()");
		return super.onBind(intent);
	}
}
