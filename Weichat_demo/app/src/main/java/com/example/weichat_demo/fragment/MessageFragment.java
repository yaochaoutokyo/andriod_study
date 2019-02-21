package com.example.weichat_demo.fragment;

import android.content.Context;
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
import com.example.weichat_demo.entity.FriendBaseInfo;
import com.example.weichat_demo.entity.SpinnerFriend;
import com.example.weichat_demo.adapter.UniversalAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yaochao on 2019/02/12
 */
public class MessageFragment extends Fragment {

	private LinkedList<FriendBaseInfo> friendBaseInfos = new LinkedList<>();
	private UniversalAdapter universalAdapter;
	private LinkedList<SpinnerFriend> spinnerFriends = new LinkedList<>();
	private List<String> names = new ArrayList<>();

	private ListView listView1;
	private Spinner spinner1;
	private MultiAutoCompleteTextView macView;
	private Context mContext;
	boolean spinnerSelected = false;


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
				holder.setImageResource(R.id.figure,obj.getIconId());
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
				bundle.putInt("iconId", friendBaseInfos.get(position).getIconId());
				bundle.putCharSequence("name", friendBaseInfos.get(position).getName());
				bundle.putCharSequence("says", friendBaseInfos.get(position).getSays());
				intent.putExtras(bundle);
				startActivity(intent);
				// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		});

		// 下拉列表功能
		spinner1 = view.findViewById(R.id.spinner1);
		UniversalAdapter<SpinnerFriend> spinnerFriendUniversalAdapter =
				new UniversalAdapter<SpinnerFriend>(spinnerFriends, R.layout.spinner_icon_info_item) {
					@Override
					public void bindView(ViewHolder holder, SpinnerFriend obj) {
						holder.setImageResource(R.id.figure_spinner, obj.getIconId());
						holder.setText(R.id.name_spinner, obj.getName());
					}
				};
		spinner1.setAdapter(spinnerFriendUniversalAdapter);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (spinnerSelected) { // 因为spinner会默认选择第0个对象，并触发一次onItemSelected事件
//					Toast.makeText(mContext, "selected: " + spinnerFriends.get(position).getName(),
//							Toast.LENGTH_SHORT).show();
				} else {
					spinnerSelected = true;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

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
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon1,"Tom","studying Andriod now" ));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon2, "Jerry","fight"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon3,"Pick", "studying IOS now"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon4,"Lua","building sever now"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon5, "Tomas", "shitting"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon6, "Herry", "peeing"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon7, "Peter", "deep dark fantasy"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon8, "Van", "Van youxi"));
		friendBaseInfos.add(new FriendBaseInfo(R.mipmap.icon9, "Bottom", "I'm bottom"));

		for (int i = 0; i < friendBaseInfos.size(); i++) {
			spinnerFriends.add(new SpinnerFriend(friendBaseInfos.get(i).getIconId(), friendBaseInfos.get(i).getName()));
		}
	}
}
