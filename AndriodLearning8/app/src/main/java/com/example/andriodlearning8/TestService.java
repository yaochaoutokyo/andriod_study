package com.example.andriodlearning8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {
	public TestService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("TestService", "onBind()");
		return null;
	}

	@Override
	public void onCreate() {
		Log.i("TestService", "onCreate()");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("TestService", "onStartCommand()");

		// service不会开辟一条新的线程，当在service里面运行耗时操作时可能触发ANR，所以耗时操作需要在intentService中进行
//		try {
//			Thread.sleep(10000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.i("TestService", "onDestroy()");
		super.onDestroy();
	}
}
