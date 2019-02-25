package com.example.weichat_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.weichat_demo.domain.FriendDO;
import com.example.weichat_demo.template.MyActivity;
import com.example.weichat_demo.utils.BitmapTransferUtil;
import com.example.weichat_demo.utils.FriendRealmOp;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaochao on 2019/02/06
 */
@Deprecated
public class FriendChooseActivity extends MyActivity {

	private List<Bitmap> bitmaps = new LinkedList<>();
	private Realm realm;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_best_friend_activity);

		initData();

		GridView gridView = findViewById(R.id.grid_figure);
		BaseAdapter baseAdapter = new BaseAdapter() {
			@Override
			public int getCount() {
				return bitmaps.size();
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
				imageView.setImageBitmap(bitmaps.get(position));
				return imageView;
			}
		};
		gridView.setAdapter(baseAdapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putInt("bestFriendId", position);
				intent.putExtras(bundle);
				setResult(0x123, intent);
				finish();
			}
		});
	}

	private void initData() {
		realm = FriendRealmOp.getRealm();
		RealmResults<FriendDO> friendDOs = FriendRealmOp.selectAll(realm);
		for (int i = 0; i < friendDOs.size(); i++) {
			bitmaps.add(BitmapTransferUtil.BytesToBitmap(friendDOs.get(i).getIconBytes()));
		}
		realm.close();
	}
}
