package com.example.weichat_demo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weichat_demo.entity.User;
import com.example.weichat_demo.tools.ActivityCollector;
import com.example.weichat_demo.tools.BaseActivity;
import com.example.weichat_demo.tools.UserCollecter;

/**
 * Created by yaochao on 2019/02/09
 */
public class LoginActivity extends BaseActivity {

	private EditText etAccount, etPassword;
	private Button btnLogin, btnSignUp;
	private boolean isExit = false;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_acitivity_layout);
		bindView();

		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = etAccount.getText().toString();
				String password = etPassword.getText().toString();
				if (!account.equals("") && !password.equals("")) {
					UserCollecter.add(new User(account, password));
					Toast.makeText(LoginActivity.this, "created new account: " + account,
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(LoginActivity.this, "account and password can't be null ",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = etAccount.getText().toString();
				String password = etPassword.getText().toString();
				User user = new User(account,password);
				if (UserCollecter.contains(user)) {
					Toast.makeText(LoginActivity.this, "Login successfully",
							Toast.LENGTH_SHORT).show();
					UserCollecter.setCurrentUser(account, password);
					Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(LoginActivity.this, "Login failed, please register first",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	void bindView() {
		etAccount = findViewById(R.id.edittext_account);
		etPassword = findViewById(R.id.edittext_password);
		btnLogin = findViewById(R.id.button_login);
		btnSignUp = findViewById(R.id.button_signup);
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
			if (! isExit) {
				isExit = true;
				Toast.makeText(getApplicationContext(), "double click to exit the App", Toast.LENGTH_LONG).show();
				exitHandler.sendEmptyMessageDelayed(0x222, 2000);
			} else {
				appExit(this);
			}
		}
		return false;
	}

	public void appExit(Context context) {
		try {
			ActivityCollector.finishAll();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception ignored) {}
	}
}
