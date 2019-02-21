package com.example.androidlearning14;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private TextView txtTopBar;
	private Button btnGoTo2;

	private TextView txtMessage;
	private TextView txtContact;
	private TextView txtMoments;
	private TextView txtAccount;
	private FragmentManager fManager;
	private ContentFragment fg1, fg2, fg3, fg4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);

		bindView();
		txtMessage.performClick();
	}

	private void bindView() {
		txtTopBar = findViewById(R.id.text_top_bar);
		btnGoTo2 = findViewById(R.id.btn_goto_main2);

		txtMessage = findViewById(R.id.text_message);
		txtContact = findViewById(R.id.text_channel);
		txtMoments = findViewById(R.id.text_better);
		txtAccount = findViewById(R.id.text_my);

		txtMessage.setOnClickListener(this);
		txtContact.setOnClickListener(this);
		txtMoments.setOnClickListener(this);
		txtAccount.setOnClickListener(this);

		fManager = getSupportFragmentManager();

		btnGoTo2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Main2Activity.class);
				startActivity(intent);
			}
		});
	}

	private void resetSelected() {
		txtMessage.setSelected(false);
		txtContact.setSelected(false);
		txtMoments.setSelected(false);
		txtAccount.setSelected(false);
	}

	private void hideAllFragment(FragmentTransaction ft) {
		if (fg1 != null) {
			ft.hide(fg1);
		}
		if (fg2 != null) {
			ft.hide(fg2);
		}
		if (fg3 != null) {
			ft.hide(fg3);
		}
		if (fg4 != null) {
			ft.hide(fg4);
		}
	}

	@Override
	public void onClick(View v) {
		resetSelected();
		FragmentTransaction ft = fManager.beginTransaction();
		hideAllFragment(ft);
		switch (v.getId()) {
			case R.id.text_message:
				txtMessage.setSelected(true);
				txtTopBar.setText("Message");
				if (fg1 == null) {
					fg1 = ContentFragment.newInstance("Message", "message from your friends");
					ft.add(R.id.content,fg1);
				} else {
					ft.show(fg1);
				}
				break;
			case R.id.text_channel:
				txtContact.setSelected(true);
				txtTopBar.setText("Contact");
				if (fg2 == null) {
					fg2 = ContentFragment.newInstance("Contact", "your contact list");
					ft.add(R.id.content,fg2);
				} else {
					ft.show(fg2);
				}
				break;
			case R.id.text_better:
				txtMoments.setSelected(true);
				txtTopBar.setText("Moments");
				if (fg3 == null) {
					fg3 = ContentFragment.newInstance("Moments", "share your moments with your friends");
					ft.add(R.id.content,fg3);
				} else {
					ft.show(fg3);
				}
				break;
			case R.id.text_my:
				txtAccount.setSelected(true);
				txtTopBar.setText("Account");
				if (fg4 == null) {
					fg4 = ContentFragment.newInstance("Account", "personalize your account");
					ft.add(R.id.content,fg4);
				} else {
					ft.show(fg4);
				}
				break;
			default:
				break;
		}
		ft.commit();
	}
}
