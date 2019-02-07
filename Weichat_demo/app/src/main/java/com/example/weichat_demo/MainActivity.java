package com.example.weichat_demo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private LinkedList<Friend> friends = new LinkedList<>();
	private FriendAdpater friendAdpater;
	private UniversalAdapter universalAdapter;
	private LinkedList<SpinnerFriend> spinnerFriends = new LinkedList<>();
	private List<String> names = new ArrayList<>();
	private boolean isExit = false;

	private boolean spinnerSelected = false;

	public void fillData() {
		friends.add(new Friend(R.mipmap.icon1,"Tom","studying Andriod now" ));
		friends.add(new Friend(R.mipmap.icon2, "Jerry","fight"));
		friends.add(new Friend(R.mipmap.icon3,"Pick", "studying IOS now"));
		friends.add(new Friend(R.mipmap.icon4,"Lua","building sever now"));
		friends.add(new Friend(R.mipmap.icon5, "Tomas", "shitting"));
		friends.add(new Friend(R.mipmap.icon6, "Herry", "peeing"));
		friends.add(new Friend(R.mipmap.icon7, "Peter", "deep dark fantasy"));
		friends.add(new Friend(R.mipmap.icon8, "Van", "Van youxi"));
		friends.add(new Friend(R.mipmap.icon9, "Bottom", "I'm bottom"));

		for (int i = 0; i < friends.size(); i++) {
			spinnerFriends.add(new SpinnerFriend(friends.get(i).getIconId(), friends.get(i).getName()));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("test", "Main-OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		fillData();
		friendAdpater = new FriendAdpater(friends, MainActivity.this);
		universalAdapter = new UniversalAdapter<Friend>(friends, R.layout.icon_info_item) {
			@Override
			public void bindView(ViewHolder holder, Friend obj) {
				holder.setImageResource(R.id.figure,obj.getIconId());
				holder.setText(R.id.name, obj.getName());
				holder.setText(R.id.says, obj.getSays());
			}
		};

		final ListView listView1 = findViewById(R.id.listview1);
		//listView1.setAdapter(friendAdpater);
		listView1.setAdapter(universalAdapter);

		listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, DetailFriendActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("iconId", friends.get(position).getIconId());
				bundle.putCharSequence("name", friends.get(position).getName());
				bundle.putCharSequence("says", friends.get(position).getSays());
				intent.putExtras(bundle);
				startActivity(intent);
				// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		});

		UniversalAdapter<SpinnerFriend> spinnerFriendUniversalAdapter =
				new UniversalAdapter<SpinnerFriend>(spinnerFriends, R.layout.spinner_icon_info_item) {
			@Override
			public void bindView(ViewHolder holder, SpinnerFriend obj) {
				holder.setImageResource(R.id.figure_spinner, obj.getIconId());
				holder.setText(R.id.name_spinner, obj.getName());
			}
		};
		Spinner spinner1 = findViewById(R.id.spinner1);
		spinner1.setAdapter(spinnerFriendUniversalAdapter);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (spinnerSelected) { // 因为spinner会默认选择第0个对象，并触发一次onItemSelected事件
					Toast.makeText(MainActivity.this, "selected: " + spinnerFriends.get(position).getName(),
							Toast.LENGTH_SHORT).show();
				} else {
					spinnerSelected = true;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


		MultiAutoCompleteTextView macView = findViewById(R.id.mac_textview1);
		for (int i = 0; i < friends.size(); i++) {
			names.add(friends.get(i).getName());
		}
		ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_dropdown_item_1line, names);
		macView.setAdapter(namesAdapter);
		macView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
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
			Log.i("onKeyDown", "KeyEvent.KEYCODE_BACK");
			if (! isExit) {
				isExit = true;
				Toast.makeText(getApplicationContext(), "double click to exit the App", Toast.LENGTH_LONG).show();
				exitHandler.sendEmptyMessageDelayed(0x222, 2000);
			} else {
				System.exit(0);
			}
		}
		return false;
	}

	@Override
	protected void onStart() {
		Log.i("test", "Main-OnStart()");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.i("test", "Main-OnResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i("test", "Main-OnPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i("test", "Main-OnStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i("test", "Main-OnDestroy()");
		super.onDestroy();
	}
}