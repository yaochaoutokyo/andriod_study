package com.example.weichat_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by yaochao on 2019/02/03
 */
public class FriendAdpater extends BaseAdapter {

	private LinkedList<Friend> friends;

	private Context context;

	public FriendAdpater() {
	}

	public FriendAdpater(LinkedList<Friend> friends, Context context) {
		this.friends = friends;
		this.context = context;
	}

	@Override
	public int getCount() {
		return friends.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//
//		convertView = LayoutInflater.from(context).inflate(R.layout.icon_info_item, parent,false);
//		ImageView icon = convertView.findViewById(R.id.figure);
//		TextView name = convertView.findViewById(R.id.name);
//		TextView says = convertView.findViewById(R.id.says);
//		icon.setImageResource(friends.get(position).getIconId());
//		name.setText(friends.get(position).getName());
//		says.setText(friends.get(position).getSays());
//
//		return convertView;
//	}

	// 使用ViewHolder设计模式，避免每次getView方法被调用时都重新加载一遍所有资源
	// 优化后代码
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FriendViewHolder friendViewHolder; //
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.icon_info_item, parent,false);
			friendViewHolder = new FriendViewHolder();
			friendViewHolder.icon = convertView.findViewById(R.id.figure);
			friendViewHolder.name = convertView.findViewById(R.id.name);
			friendViewHolder.says = convertView.findViewById(R.id.says);
			convertView.setTag(friendViewHolder); // 保存ViewHolder
		} else {
			friendViewHolder = (FriendViewHolder) convertView.getTag(); // 取出ViewHolder
		}

		friendViewHolder.icon.setImageResource(friends.get(position).getIconId());
		friendViewHolder.name.setText(friends.get(position).getName());
		friendViewHolder.says.setText(friends.get(position).getSays());

		return convertView;
	}

	class FriendViewHolder {
		public ImageView icon;

		public TextView name;

		public TextView says;
	}
}
