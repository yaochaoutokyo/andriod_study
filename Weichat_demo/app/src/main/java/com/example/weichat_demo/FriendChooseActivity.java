package com.example.weichat_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by yaochao on 2019/02/06
 */
public class FriendChooseActivity extends AppCompatActivity {
	public int[] iconIds = {
			R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4, R.mipmap.icon5,
			R.mipmap.icon6, R.mipmap.icon7, R.mipmap.icon8, R.mipmap.icon9
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		Log.i("test", "Choose-OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_best_friend_activity);

		GridView gridView = findViewById(R.id.grid_figure);
		BaseAdapter baseAdapter = new BaseAdapter() {
			@Override
			public int getCount() {
				return iconIds.length;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageView;
				if (convertView == null) {
					imageView = new ImageView(FriendChooseActivity.this);
					imageView.setAdjustViewBounds(true);
					imageView.setMaxHeight(120);
					imageView.setMaxWidth(120);
					imageView.setPadding(5,5,5,5);
				} else {
					imageView = (ImageView) convertView;
				}
				imageView.setImageResource(iconIds[position]);
				return imageView;
			}
		};
		gridView.setAdapter(baseAdapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putInt("iconIdBestFriend", iconIds[position]);
				intent.putExtras(bundle);
				setResult(0x123, intent);
				finish();
			}
		});
	}


	@Override
	protected void onStart() {
		Log.i("test", "Choose-OnStart()");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.i("test", "Choose-OnResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i("test", "Choose-OnPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i("test", "Choose-OnStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i("test", "Choose-OnDestroy()");
		super.onDestroy();
	}
}
