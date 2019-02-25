package com.example.weichat_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.weichat_demo.adapter.UniversalAdapter;
import com.example.weichat_demo.domain.FriendDO;
import com.example.weichat_demo.entity.FriendBaseInfo;
import com.example.weichat_demo.entity.SpinnerFriend;
import com.example.weichat_demo.template.MyActivity;
import com.example.weichat_demo.utils.BitmapTransferUtil;
import com.example.weichat_demo.utils.FriendRealmOp;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaochao on 2019/02/06
 */
public class DetailFriendActivity extends MyActivity {

	private ImageView imgIcon;
	private EditText edTxtName, edTxtSays, edTxtPhone, edTxtEmail;
	private Button btnApplyChange;
	private Realm realm;
	private Integer currentId, bestFriendId;

	private Spinner spinnerBestFriend;
	private boolean isSelected = false;
	private LinkedList<SpinnerFriend> spinnerFriends = new LinkedList<>();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_friend_info_activity);
		// 获取当前Friend的id
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		currentId = bundle.getInt("id");

		fillData();

		bindView();

		readBestFriendIcon();

		btnApplyChange.setOnClickListener((v) -> {
			FriendDO change = new FriendDO();
			change.setId(currentId);
			change.setName(edTxtName.getText().toString());
			change.setSays(edTxtSays.getText().toString());
			change.setBestFriendId(bestFriendId);
			change.setPhoneNumber(edTxtPhone.getText().toString());
			change.setEmail(edTxtEmail.getText().toString());
			Bitmap currentIcon = ((BitmapDrawable) imgIcon.getDrawable()).getBitmap();
			change.setIconBytes(BitmapTransferUtil.BitmapToBytes(currentIcon));
			FriendRealmOp.update(change);
			Toast.makeText(getApplicationContext(),"applied changes", Toast.LENGTH_SHORT).show();
		});

		spinnerBestFriend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isSelected) {
					isSelected = true;
					bestFriendId = position;
					Toast.makeText(getApplicationContext(), "selected friend " +
							spinnerFriends.get(position).getName(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void bindView() {
		edTxtName = findViewById(R.id.big_name);
		edTxtSays = findViewById(R.id.big_says);
		edTxtPhone = findViewById(R.id.big_phone);
		edTxtEmail = findViewById(R.id.big_email);
		btnApplyChange = findViewById(R.id.btn_edit_apply);
		imgIcon = findViewById(R.id.big_figure);

		// 下拉列表功能
		spinnerBestFriend = findViewById(R.id.spinner_best_friend);
		spinnerBestFriend.setAdapter(new UniversalAdapter<SpinnerFriend>(spinnerFriends,
				R.layout.spinner_icon_info_item) {
			@Override
			public void bindView(ViewHolder holder, SpinnerFriend obj) {
				holder.setImageBitmap(R.id.figure_spinner, obj.getIconBitmap());
				holder.setText(R.id.name_spinner, obj.getName());
			}
		});
	}

	private void readBestFriendIcon() {

		// 获取当前Friend的所有信息
		realm = FriendRealmOp.getRealm();
		RealmResults<FriendDO> result = FriendRealmOp.selectById(currentId, realm);
		imgIcon.setImageBitmap(BitmapTransferUtil.BytesToBitmap(result.get(0).getIconBytes()));
		edTxtName.setText(result.get(0).getName());
		edTxtSays.setText(result.get(0).getSays());
		edTxtPhone.setText(result.get(0).getPhoneNumber());
		edTxtEmail.setText(result.get(0).getEmail());

		// 获取最好朋友的头像
		bestFriendId = result.get(0).getBestFriendId();
		if (bestFriendId != null) {
			spinnerBestFriend.setSelection(bestFriendId);
		}
		realm.close();
	}

	public void fillData() {
		realm = FriendRealmOp.getRealm();
		RealmResults<FriendDO> friendDOs = FriendRealmOp.selectAll(realm);
		for (int i = 0; i < friendDOs.size(); i++) {
			Bitmap icon = BitmapTransferUtil.BytesToBitmap(friendDOs.get(i).getIconBytes());
			String name = friendDOs.get(i).getName();

			SpinnerFriend spiInfo = new SpinnerFriend();
			spiInfo.setIconBitmap(icon);
			spiInfo.setName(name);
			spinnerFriends.add(spiInfo);
		}
		realm.close();
	}
}
