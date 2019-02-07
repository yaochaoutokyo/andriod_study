package com.example.andriodlearning7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView textView;
	private ProgressBar progressBar;
	private Button button;
	private MyAsyncTask myAsyncTask;
	private boolean pressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);setContentView(R.layout.activity_main);

		textView = findViewById(R.id.textView);
		progressBar = findViewById(R.id.progressBar);
		button = findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pressed) {
					pressed = false;
					myAsyncTask.onCancelled();
					button.setText("start");
				} else {
					pressed = true;
					button.setText("cancel");
					myAsyncTask = new MyAsyncTask(textView,progressBar,button);
					myAsyncTask.execute(1000);
				}
			}
		});
	}

}
