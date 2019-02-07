package com.example.andriodlearning3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.LinkedList;

/**
 * Created by yaochao on 2019/02/03
 */
public class CompAdapter extends BaseAdapter {

	private Context context;

	private LinkedList<Comp> compList;

	public CompAdapter() {

	}

	public CompAdapter(Context context, LinkedList<Comp> compList) {
		this.context = context;
		this.compList = compList;
	}

	@Override
	public int getCount() {
		return compList.size();
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
		final int index = position;
		CompViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_with_check_box, parent, false);
			holder = new CompViewHolder();
			holder.checkBox1 = convertView.findViewById(R.id.checkbox1);
			holder.editText1 = convertView.findViewById(R.id.edittext1);
			holder.button1 = convertView.findViewById(R.id.btn1);
			convertView.setTag(holder);
		} else {
			holder = (CompViewHolder) convertView.getTag();
		}

		holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					compList.get(index).setCheckedStatus(true);
				} else {
					compList.get(index).setCheckedStatus(false);
				}
			}
		});
		holder.checkBox1.setChecked(compList.get(position).getCheckedStatus());
		return convertView;
	}

	public void add() {
		compList.add(new Comp());
		notifyDataSetChanged();
	}

	public void del() {
		if (!compList.isEmpty()) {
			compList.removeFirst();
			notifyDataSetChanged();
		}
	}
}
