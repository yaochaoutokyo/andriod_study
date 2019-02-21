package com.example.androidlearning14;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity implements
		RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

	private RadioGroup radioGroup;
	private ContentFragmentAdapter contentFragmentAdapter;
	private TextView txtTopBar;
	private ViewPager contentViewPager;
	private RadioButton messageRBtn, contactRBtn, momentsRBtn, accountRBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout_3);

		bindView();
	}

	private void bindView() {
		txtTopBar = findViewById(R.id.text_top_bar_2);
		radioGroup = findViewById(R.id.radiogroup_bottom_bar);
		radioGroup.setOnCheckedChangeListener(this);
		messageRBtn = findViewById(R.id.radio_message);
		contactRBtn = findViewById(R.id.radio_contact);
		momentsRBtn = findViewById(R.id.radio_moments);
		accountRBtn = findViewById(R.id.radio_account);

		contentViewPager = findViewById(R.id.viewpager_content);
		contentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager());
		contentViewPager.setAdapter(contentFragmentAdapter);
		contentViewPager.setCurrentItem(0);
		contentViewPager.addOnPageChangeListener(this);
		messageRBtn.setChecked(true);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.radio_message:
				contentViewPager.setCurrentItem(0);
				txtTopBar.setText("Message");
				break;
			case R.id.radio_contact:
				contentViewPager.setCurrentItem(1);
				txtTopBar.setText("Contact");
				break;
			case R.id.radio_moments:
				contentViewPager.setCurrentItem(2);
				txtTopBar.setText("Moments");
				break;
			case R.id.radio_account:
				contentViewPager.setCurrentItem(3);
				txtTopBar.setText("Account");
				break;
			default:
				break;
		}
	}

	@Override
	public void onPageScrolled(int i, float v, int i1) {

	}

	@Override
	public void onPageSelected(int i) {

	}

	@Override
	public void onPageScrollStateChanged(int i) {
		if (i == 2) {
			switch (contentViewPager.getCurrentItem()) {
				case 0:
					messageRBtn.setChecked(true);
					txtTopBar.setText("Message");
					break;
				case 1:
					contactRBtn.setChecked(true);
					txtTopBar.setText("Contact");
					break;
				case 2:
					momentsRBtn.setChecked(true);
					txtTopBar.setText("Moments");
					break;
				case 3:
					accountRBtn.setChecked(true);
					txtTopBar.setText("Account");
					break;
			}
		}
	}
}
