package com.example.andriodlearning8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by yaochao on 2019/02/07
 */
public class PersonAIDLService extends Service {

	private IBinder binder = new PersonQueryBinder();

	private String[] names = { "Tom", "Jerry", "Mao", "Deep", "Kim" };

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	private final class PersonQueryBinder extends IPerson.Stub {
		@Override
		public String queryPerson(int num) throws RemoteException {
			return names[num % 5];
		}
	}
}
