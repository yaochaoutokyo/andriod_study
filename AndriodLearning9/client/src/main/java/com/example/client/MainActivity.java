package com.example.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andriodlearning9.IMyAidlInterface;
import com.example.andriodlearning9.Staff;

public class MainActivity extends AppCompatActivity {

	IMyAidlInterface queryBinder;

	QueryConnection queryConnection = new QueryConnection();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = new Intent("com.example.andriodlearning9.QueryService");
		intent.setPackage("com.example.andriodlearning9");
		bindService(intent, queryConnection, BIND_AUTO_CREATE);

		Button button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (queryBinder != null) {
					try {
						String string = queryBinder.get(new Staff("yao","male")).getTitle();
						Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(MainActivity.this, "fail to connect", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	class QueryConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			if (service == null) {
				Log.i("test", "onServiceConnected()");
			}
			queryBinder = IMyAidlInterface.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("text", "onServiceDisconnected()");
			queryBinder = null;
		}
	}
}
