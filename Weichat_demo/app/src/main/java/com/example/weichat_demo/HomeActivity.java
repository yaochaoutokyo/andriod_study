package com.example.weichat_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weichat_demo.fragment.AccountFragment;
import com.example.weichat_demo.fragment.ContactFragment;
import com.example.weichat_demo.fragment.MessageFragment;
import com.example.weichat_demo.fragment.MomentsFragment;
import com.example.weichat_demo.template.MyActivity;
import com.example.weichat_demo.utils.ActivityCollector;

public class HomeActivity extends MyActivity implements RadioGroup.OnCheckedChangeListener {

	private RadioGroup radioGroup;
	private RadioButton rbtnMessage, rbtnMoments;
	private TextView txtTopBar;
	private boolean isExit = false;

	private MomentsFragment momentsFrag;
	private AccountFragment accountFrag;
	private MessageFragment messageFrag;
	private ContactFragment contactFrag;
	private FragmentManager fManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity_layout_radiobutton);
		bindView();

		rbtnMessage = findViewById(R.id.radio_message);
		rbtnMessage.setChecked(true);
	}

	private void bindView() {
		txtTopBar = findViewById(R.id.text_top_bar_2);
		radioGroup = findViewById(R.id.radiogroup_bottom_bar);
		radioGroup.setOnCheckedChangeListener(this);
		fManager = getSupportFragmentManager();
		rbtnMessage = findViewById(R.id.radio_message);
		rbtnMoments = findViewById(R.id.radio_moments);
	}

	private void hideAllFragment(FragmentTransaction ft) {
		if (messageFrag != null) {
			ft.hide(messageFrag);
		}
		if (contactFrag != null) {
			ft.hide(contactFrag);
		}
		if (momentsFrag != null) {
			ft.hide(momentsFrag);
		}
		if (accountFrag != null) {
			ft.hide(accountFrag);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction ft = fManager.beginTransaction();
		hideAllFragment(ft);
		switch (checkedId) {
			case R.id.radio_message:
				txtTopBar.setText("Message");
				if (messageFrag == null) {
					messageFrag = new MessageFragment();
					ft.add(R.id.content_2, messageFrag);
				} else {
					ft.show(messageFrag);
				}
				break;
			case R.id.radio_contact:
				txtTopBar.setText("Contact");
				if (contactFrag == null) {
					contactFrag = new ContactFragment();
					ft.add(R.id.content_2, contactFrag);
				} else {
					ft.show(contactFrag);
				}
				break;
			case R.id.radio_moments:
				txtTopBar.setText("Moments");
				if (momentsFrag == null) {
					momentsFrag = new MomentsFragment();
					ft.add(R.id.content_2, momentsFrag);
				} else {
					ft.show(momentsFrag);
				}
				break;
			case R.id.radio_account:
				txtTopBar.setText("Account");
				if (accountFrag == null) {
					accountFrag = new AccountFragment();
					ft.add(R.id.content_2, accountFrag);
				} else {
					ft.show(accountFrag);
				}
				break;
		}
		ft.commit();
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
		// webView开启时，点击返回键优先返回web页面
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (rbtnMoments.isChecked()) {
				WebView webView = findViewById(R.id.web_view_moments);
				if (webView.canGoBack()) {
					webView.goBack();
					return true;
				}
			}
		}

		// 双击退出app
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
