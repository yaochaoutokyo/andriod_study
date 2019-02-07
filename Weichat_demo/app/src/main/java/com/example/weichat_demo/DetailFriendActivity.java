package com.example.weichat_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yaochao on 2019/02/06
 */
public class DetailFriendActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		Log.i("test", "Detail-OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_friend_info_activity);

		ImageView bigIcon = findViewById(R.id.big_figure);
		TextView bigName = findViewById(R.id.big_name);
		TextView bigSays = findViewById(R.id.big_says);
		Button chooseFriendBtn = findViewById(R.id.btn_choose_friend);


		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		Integer iconId = bundle.getInt("iconId");
		String name = bundle.getCharSequence("name").toString();
		String says = bundle.getCharSequence("says").toString();
		if (iconId != null && name != null && says != null) {
			bigIcon.setImageResource(iconId);
			bigName.setText(name);
			bigSays.setText(says);
		}

		chooseFriendBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentChooseFriend = new Intent(DetailFriendActivity.this, FriendChooseActivity.class);
				startActivityForResult(intentChooseFriend, 0x111);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x111 && resultCode == 0x123) {
			Bundle bundle = data.getExtras();
			ImageView iconBestFriend = findViewById(R.id.figure_best_friend);
			iconBestFriend.setImageResource(bundle.getInt("iconIdBestFriend"));
		}
	}

	@Override
	protected void onStart() {
		Log.i("test", "Detail-OnStart()");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.i("test", "Detail-OnResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i("test", "Detail-OnPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i("test", "Detail-OnStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i("test", "Detail-OnDestroy()");
		super.onDestroy();
	}
}
