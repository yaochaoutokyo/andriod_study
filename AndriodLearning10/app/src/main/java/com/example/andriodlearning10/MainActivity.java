package com.example.andriodlearning10;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	private MyBBReceiver myBBReceiver;
	private MultiLoginBBReceiver multiLoginBBReceiver;
	private LocalBroadcastManager localBroadcastManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = findViewById(R.id.button);
		Button button1 = findViewById(R.id.button2);
		Button button2 = findViewById(R.id.button4);
		localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("dynamic_broadcast");
				sendBroadcast(intent);
			}
		});

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("static_broadcast");
				sendBroadcast(intent);
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("login_by_other");
				localBroadcastManager.sendBroadcast(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		myBBReceiver = new MyBBReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("dynamic_broadcast");
		registerReceiver(myBBReceiver, intentFilter);

		multiLoginBBReceiver = new MultiLoginBBReceiver();
		IntentFilter intentFilter1 = new IntentFilter();
		intentFilter1.addAction("login_by_other");
		localBroadcastManager.registerReceiver(multiLoginBBReceiver, intentFilter1);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(myBBReceiver);
		localBroadcastManager.unregisterReceiver(multiLoginBBReceiver);
	}

}
