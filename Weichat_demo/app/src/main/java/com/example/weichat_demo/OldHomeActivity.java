package com.example.weichat_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weichat_demo.adapter.ContentFragmentAdapter;
import com.example.weichat_demo.utils.ActivityCollector;
import com.example.weichat_demo.template.MyActivity;

@Deprecated
public class OldHomeActivity extends MyActivity implements
		RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

	private RadioGroup radioGroup;
	private ContentFragmentAdapter contentFragmentAdapter;
	private TextView txtTopBar;
	private ViewPager contentViewPager;
	private RadioButton messageRBtn, contactRBtn, momentsRBtn, accountRBtn;
	private boolean isExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity_layout);

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

	// 双击退出功能
	Handler exitHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x222) {
				isExit = false;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (contentViewPager.getCurrentItem() == 2) {
				WebView webView = findViewById(R.id.web_view_moments);
				if (webView.canGoBack()) {
					webView.goBack();
					return true;
				}
			}
		}

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!isExit) {
				isExit = true;
				Toast.makeText(getApplicationContext(), "double click to exit the App", Toast.LENGTH_LONG).show();
				exitHandler.sendEmptyMessageDelayed(0x222, 2000);
			} else {
				ActivityCollector.appExit(this);
			}
		}
		return false;
	}
}
