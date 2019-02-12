package com.example.andriodlearning9;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by yaochao on 2019/02/09
 */
public class QueryService extends Service {

	private QueryBinder queryBinder;

	@Override
	public IBinder onBind(Intent intent) {
		return new QueryBinder();
	}

	class QueryBinder extends IMyAidlInterface.Stub {
		@Override
		public Position get(Staff staff) throws RemoteException {
			switch (staff.getName()) {
				case "yao":
					return new Position("CEO", 200000);
				case "wang":
					return new Position("CFO", 180000);
				default:
					return new Position("NULL", 0);
			}
		}
	}


}
