package com.example.weichat_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.weichat_demo.StopMultiLoginInterface;
import com.example.weichat_demo.entity.User;
import com.example.weichat_demo.utils.UserCollecter;

/**
 * Created by yaochao on 2019/02/09
 */
public class StopMultiLoginService extends Service {

	public MyBinder myBinder;

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	class MyBinder extends StopMultiLoginInterface.Stub {
		@Override
		public User getCurrentUser() throws RemoteException {
			return UserCollecter.getCurrentUser();
		}
	}

}
