package com.example.androidlearning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private Button button1, button2;

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

		final RadioGroup radioGroup = findViewById(R.id.btn_group);
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
	}
}
