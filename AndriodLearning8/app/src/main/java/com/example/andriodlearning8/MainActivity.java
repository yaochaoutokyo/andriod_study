package com.example.andriodlearning8;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private Button button, button2;
	private Button button3, button4, button5;
	private Button button6;
	private Intent intent, intentBind, intentIntent;
	private TestBindService.MyBinder myBinder;
	private int count = 0;
	private String[] actions = { "ac1", "ac2", "ac3" };

	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("TestBindService", "onServiceConnected()");
			myBinder = (TestBindService.MyBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("TestBindService", "onServiceDisconnected");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = findViewById(R.id.button);
		button2 = findViewById(R.id.button2);
		intent = new Intent();
		intent.setAction("test_service");

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(intent);
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(intent);
			}
		});

		intentBind = new Intent();
		intentBind.setAction("test_bind_service");

		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		button5 = findViewById(R.id.button5);

		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bindService(intentBind, conn, Service.BIND_AUTO_CREATE);
			}
		});

		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				unbindService(conn);
			}
		});

		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "count=" +
						myBinder.getCount(),Toast.LENGTH_SHORT).show();;
			}
		});


		button6 = findViewById(R.id.button6);
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				count++;
				intentIntent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putCharSequence("param", actions[count % 3]);
				intentIntent.setAction("test_intent_service");
				intentIntent.putExtras(bundle);
				startService(intentIntent);
			}
		});
	}


}
