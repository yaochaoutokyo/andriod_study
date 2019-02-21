package com.example.androidlearning14;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

	private RadioGroup radioGroup;
	private TextView txtTopBar;
	private Button btnGoTo3;

	private ContentFragment messageFrag, contactFrag, momentsFrag, accountFrag;
	private FragmentManager fManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout_2);
		bindView();

		RadioButton radioMessage = findViewById(R.id.radio_message);
		radioMessage.setChecked(true);
	}

	private void bindView() {
		txtTopBar = findViewById(R.id.text_top_bar_2);
		radioGroup = findViewById(R.id.radiogroup_bottom_bar);
		radioGroup.setOnCheckedChangeListener(this);
		fManager = getSupportFragmentManager();
		btnGoTo3 = findViewById(R.id.btn_goto_main3);
		btnGoTo3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
				startActivity(intent);
			}
		});
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
					messageFrag = ContentFragment.newInstance("Message", "message from your friends");
					ft.add(R.id.content_2, messageFrag);
				} else {
					ft.show(messageFrag);
				}
				break;
			case R.id.radio_contact:
				txtTopBar.setText("Contact");
				if (contactFrag == null) {
					contactFrag = ContentFragment.newInstance("Contact", "your contact list");
					ft.add(R.id.content_2, contactFrag);
				} else {
					ft.show(contactFrag);
				}
				break;
			case R.id.radio_moments:
				txtTopBar.setText("Moments");
				if (momentsFrag == null) {
					momentsFrag = ContentFragment.newInstance("Moments", "share your moments with your friends");
					ft.add(R.id.content_2, momentsFrag);
				} else {
					ft.show(momentsFrag);
				}
				break;
			case R.id.radio_account:
				txtTopBar.setText("Account");
				if (accountFrag == null) {
					accountFrag = ContentFragment.newInstance("Account", "personalize your account");
					ft.add(R.id.content_2, accountFrag);
				} else {
					ft.show(accountFrag);
				}
				break;
		}
		ft.commit();
	}
}
