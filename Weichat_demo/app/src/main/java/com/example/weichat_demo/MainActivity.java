package com.example.weichat_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

	private LinkedList<Friend> friends = new LinkedList<>();
	private FriendAdpater friendAdpater;

	public void fillData() {
		friends.add(new Friend(R.mipmap.icon1,"Tom","studying Andriod now" ));
		friends.add(new Friend(R.mipmap.icon2, "Jerry","fight"));
		friends.add(new Friend(R.mipmap.icon3,"Pick", "studying IOS now"));
		friends.add(new Friend(R.mipmap.icon4,"Lua","building sever now"));
		friends.add(new Friend(R.mipmap.icon5, "Bob", "shitting"));
		friends.add(new Friend(R.mipmap.icon6, "Herry", "peeing"));
		friends.add(new Friend(R.mipmap.icon7, "Zara", "deep dark fantasy"));
		friends.add(new Friend(R.mipmap.icon8, "Van", "Van youxi"));
		friends.add(new Friend(R.mipmap.icon9, "Bottom", "I'm bottom"));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		fillData();
		friendAdpater = new FriendAdpater(friends, MainActivity.this);
		final ListView listView1 = findViewById(R.id.listview1);
		listView1.setAdapter(friendAdpater);

		listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this, friends.get(position).getName() +
						": " + friends.get(position).getSays(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}