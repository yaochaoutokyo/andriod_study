package com.example.app2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weichat_demo.StopMultiLoginInterface;
import com.example.weichat_demo.entity.User;

/**
 * Created by yaochao on 2019/02/09
 */
public class LoginActivity extends AppCompatActivity {

	private EditText etAccount, etPassword;
	private Button btnLogin, btnSignUp;
	private StopMultiLoginInterface binder;
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = StopMultiLoginInterface.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			binder = null;
		}
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		bindView();

		Intent intent = new Intent("android.intent.action.StopMultiLoginService");
		intent.setPackage("com.example.weichat_demo");
		bindService(intent, serviceConnection, BIND_AUTO_CREATE);

		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = etAccount.getText().toString();
				String password = etPassword.getText().toString();
				Toast.makeText(LoginActivity.this, "created new account: " + account,
						Toast.LENGTH_SHORT).show();
			}
		});

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = etAccount.getText().toString();
				String password = etPassword.getText().toString();
				User user = new User(account,password);
				Toast.makeText(LoginActivity.this, "Login successful",
								Toast.LENGTH_SHORT).show();
				try {
					if (binder != null) {
						if (account.equals(binder.getCurrentUser().getAccount()) &&
								password.equals(binder.getCurrentUser().getPasswrod())) {
							Intent intentToReceiver = new Intent("android.intent.action.MultiLoginBBReceiver");
							sendBroadcast(intentToReceiver); // 高版本不兼容
						}
					}
				} catch (RemoteException e) {
					e.printStackTrace();
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
}
