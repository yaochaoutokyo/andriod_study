package com.example.androidlearning1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

	private Button button1, button2, botton6;
	private RadioGroup radioGroup;
	private ToggleButton toggleButton1;
	private CheckBox checkBox1, checkBox2;
	private ProgressBar progressBar1;
	private Handler handler;
	private Runnable runnable;
	private SeekBar seekBar1;
	private TextView textView4;
	private RatingBar ratingBar1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative_layout);
		TextView textView = findViewById(R.id.textview1);
		String htmlString = "<a href = 'http://www.baidu.com' target = '_blank' >Baidu</a>";
		textView.setText(Html.fromHtml(htmlString));
		textView.setMovementMethod(LinkMovementMethod.getInstance());

		EditText editText1 = findViewById(R.id.edittext1);
		editText1.requestFocus();

		button1 = findViewById(R.id.btn1);
		button2 = findViewById(R.id.btn2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (button2.getText().toString().equals(getResources().getText(R.string.normal_button))) {
					button1.setEnabled(false);
					button2.setText(getResources().getText(R.string.strange_button));
				} else {
					button1.setEnabled(true);
					button2.setText(getResources().getText(R.string.normal_button));
				}
			}
		});

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				button1.setText("pressed");
				button1.setBackgroundResource(R.color.bbutton_danger);
			}
		});

		button1.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					button1.setText("press");
					button1.setBackgroundResource(R.color.bbutton_danger_pressed);
				}

				if (event.getAction() == MotionEvent.ACTION_UP) {
					button1.setText("loose");
					button1.setBackgroundResource(R.color.text_font_white);
				}
				return false;
			}
		});

		radioGroup = findViewById(R.id.btn_group);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton radioButton = findViewById(checkedId);
				Toast.makeText(getApplicationContext(), radioButton.getText(),Toast.LENGTH_SHORT).show();
			}
		});

		Button button4 = findViewById(R.id.btn4);
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < radioGroup.getChildCount(); i++) {
					RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
					if (radioButton.isChecked()) {
						Toast.makeText(getApplicationContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		checkBox1 = findViewById(R.id.checkbox1);
		checkBox2 = findViewById(R.id.checkbox2);
		checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(), checkBox1.getText(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(), checkBox2.getText(), Toast.LENGTH_SHORT).show();;
				}
			}
		});

		Button button5 = findViewById(R.id.btn5);
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String string = "";
				if (checkBox1.isChecked()) {
					string = string + checkBox1.getText().toString();
				}
				if (checkBox2.isChecked()) {
					string = string + checkBox2.getText().toString();
				}
				if (!string.equals("")) {
					Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
				}
			}
		});

		Switch switch1 = findViewById(R.id.switch1);
		toggleButton1 = findViewById(R.id.togglebtn1);
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					toggleButton1.setChecked(true);
					Toast.makeText(getApplicationContext(), toggleButton1.getText().toString(),
							Toast.LENGTH_SHORT).show();
				}
				else {
					toggleButton1.setChecked(false);
					Toast.makeText(getApplicationContext(), toggleButton1.getText().toString(),
							Toast.LENGTH_SHORT).show();
				}
			}
		});


		handler = new Handler();
		runnable = new Runnable() {
			int i = 0;
			@Override
			public void run() {
				i = (i + 10)%100;
				progressBar1.setProgress(i);
				if (i == 0) {
					botton6.setText("start");
					botton6.setEnabled(true);
					Toast.makeText(getApplicationContext(), "finished", Toast.LENGTH_SHORT).show();
					handler.removeCallbacks(this);
				} else {
					handler.postDelayed(this,500);
				}
			}
		};
		progressBar1 = findViewById(R.id.progressbar1);
		botton6 = findViewById(R.id.btn6);
		botton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				botton6.setEnabled(false);
				botton6.setText("wait...");
				handler.post(runnable);
			}
		});


		seekBar1 = findViewById(R.id.seekbar1);
		textView4 = findViewById(R.id.textview4);
		seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				textView4.setText(seekBar1.getProgress()+"/100");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(), "progress: " + seekBar1.getProgress() + "/100",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(), "progress: " + seekBar1.getProgress() + "/100",
						Toast.LENGTH_SHORT).show();
			}
		}	);

		ratingBar1 = findViewById(R.id.ratingbar1);
		ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				Toast.makeText(getApplicationContext(), "Rating: " + String.valueOf(rating),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
