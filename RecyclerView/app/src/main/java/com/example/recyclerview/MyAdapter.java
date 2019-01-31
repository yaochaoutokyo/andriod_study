package com.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by intern-yao on 2019-01-31
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

	private ArrayList<String> mData;

	public MyAdapter(ArrayList<String> data) {
		this.mData = data;
	}

	public void updateData(ArrayList<String> data) {
		this.mData = data;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// 实例化展示的view
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
		// 实例化viewholder
		ViewHolder viewHolder = new ViewHolder(v);
		return viewHolder;
	}
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// 绑定数据
		holder.mTv.setText(mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData == null ? 0 : mData.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		TextView mTv;

		public ViewHolder(View itemView) {
			super(itemView);
			mTv = itemView.findViewById(R.id.item_tv);
		}
	}
}
