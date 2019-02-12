package com.example.andriodlearning13;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andriodlearning13.fragment.DynamicFragment1;

public class MainActivity extends AppCompatActivity {

	private Button btnSf, btnAc, btnAc2;

	private TextView textSf, textDf1, textDf2;

	DynamicFragment1 dynamicFragment1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("Activity", "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		btnSf = findViewById(R.id.btn_sf);
		dynamicFragment1 = new DynamicFragment1();
		btnSf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 getSupportFragmentManager().beginTransaction()
						.replace(R.id.root, dynamicFragment1)
						.addToBackStack(null)
						.commit();
				 new Handler() {
					 @Override
					 public void handleMessage(Message msg) {
					 	if (msg.what == 0x11) {
					 		Toast.makeText(getApplicationContext(), "got msg", Toast.LENGTH_SHORT).show();
						}
					 }
				 }.sendEmptyMessage(0x11);
			}
		});

		btnAc = findViewById(R.id.btn_ac);

		btnAc2 = findViewById(R.id.btn_ac2);
		btnAc2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = textSf.getText().toString();
				if (textDf1 != null) {
					text = text + textDf1.getText().toString();
				}
				if (textDf2 != null) {
					text = text + textDf2.getText().toString();
				}
				Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("Activity", "onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Activity", "onResume()");
		textSf = findViewById(R.id.text_sf);
		if (dynamicFragment1.getView() != null) {
			textDf1 = dynamicFragment1.getView().findViewById(R.id.text_df1);
		}
		textDf2 = findViewById(R.id.text_df2);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("Activity", "onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("Activity", "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Activity", "onDestroy()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("Activity", "onRestart()");
	}
}
