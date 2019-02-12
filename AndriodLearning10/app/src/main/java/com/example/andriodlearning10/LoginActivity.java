package com.example.andriodlearning10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by yaochao on 2019/02/09
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

	EditText etAccount, etPassword;
	Button btnLogin;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		bindView();
	}

	void bindView() {
		etAccount = findViewById(R.id.editText);
		etPassword = findViewById(R.id.editText2);
		btnLogin = findViewById(R.id.button3);
		btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String account = etAccount.getText().toString();
		String password = etPassword.getText().toString();
		if (account.equals("admin") && password.equals("123456")) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
		}
	}
}
