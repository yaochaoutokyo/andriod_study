package com.example.andriodlearning8;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestBindService extends Service {

	private int count;

	private boolean quite = false;

	class MyBinder extends Binder {

		public int getCount() {
			return count;
		}
	}

	public TestBindService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("TestBindService", "onBind()");
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("TestBindService", "onCreate()");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!quite) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					count++;
				}
			}
		}).start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("TestBindService", "onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("TestBindService", "onDestroy()");
		quite = true;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("TestBindService", "onUnbind()");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Log.i("TestBindService", "onRebind()");
	}
}
