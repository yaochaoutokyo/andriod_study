package com.example.weichat_demo.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import com.example.weichat_demo.DetailFriendActivity;
import com.example.weichat_demo.R;
import com.example.weichat_demo.domain.FriendDO;
import com.example.weichat_demo.entity.FriendBaseInfo;
import com.example.weichat_demo.entity.SpinnerFriend;
import com.example.weichat_demo.adapter.UniversalAdapter;
import com.example.weichat_demo.utils.BitmapTransferUtil;
import com.example.weichat_demo.utils.FriendRealmOp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaochao on 2019/02/12
 */
public class MessageFragment extends Fragment {

	private LinkedList<FriendBaseInfo> friendBaseInfos = new LinkedList<>();
	private UniversalAdapter universalAdapter;
	private List<String> names = new ArrayList<>();
	private ListView listView1;
	private MultiAutoCompleteTextView macView;
	private Context mContext;
	private Realm realm;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.message_fragment_layout, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		fillData();
		listView1 = view.findViewById(R.id.listview1);
		universalAdapter = new UniversalAdapter<FriendBaseInfo>(friendBaseInfos, R.layout.icon_info_item) {
			@Override
			public void bindView(ViewHolder holder, FriendBaseInfo obj) {
				holder.setImageBitmap(R.id.figure,obj.getIconBitmap());
				holder.setText(R.id.name, obj.getName());
				holder.setText(R.id.says, obj.getSays());
			}
		};
		listView1.setAdapter(universalAdapter);
		listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(mContext, DetailFriendActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("id", position);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		// 自动查询功能
		macView = view.findViewById(R.id.mac_textview1);
		for (int i = 0; i < friendBaseInfos.size(); i++) {
			names.add(friendBaseInfos.get(i).getName());
		}
		ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_dropdown_item_1line, names);
		macView.setAdapter(namesAdapter);
		macView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}

	public void fillData() {
		realm = FriendRealmOp.getRealm();
		RealmResults<FriendDO> friendDOs = FriendRealmOp.selectAll(realm);
		for (int i = 0; i < friendDOs.size(); i++) {
			Bitmap icon = BitmapTransferUtil.BytesToBitmap(friendDOs.get(i).getIconBytes());
			String name = friendDOs.get(i).getName();
			String says = friendDOs.get(i).getSays();

			FriendBaseInfo info = new FriendBaseInfo();
			info.setIconBitmap(icon);
			info.setName(name);
			info.setSays(says);
			friendBaseInfos.add(info);
		}
		realm.close();
	}
}
