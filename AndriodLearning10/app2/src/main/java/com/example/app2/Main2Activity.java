package com.example.app2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

	private Button button, button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		bindView();

	}

	public void bindView() {
		button = findViewById(R.id.button);
		button1 = findViewById(R.id.button2);
		button.setOnClickListener(this);
		button1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button:
				sendBroadcast(new Intent("dynamic_broadcast"));
				break;

			case R.id.button2:
				sendBroadcast(new Intent("static_broadcast"));
				break;

			default:
				break;
		}
	}
}
