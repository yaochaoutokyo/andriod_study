package com.example.weichat_demo;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by yaochao on 2019/02/05
 */
public abstract class UniversalAdapter<T> extends BaseAdapter {

	private LinkedList<T> mData;

	private int mLayoutRes;

	public UniversalAdapter() {
	}

	public UniversalAdapter(LinkedList<T> mData, int mLayoutRes) {
		this.mData = mData;
		this.mLayoutRes = mLayoutRes;
	}

	@Override
	public int getCount() {
		return mData != null ? mData.size() : 0;
	}

	@Override
	public T getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public abstract void bindView(ViewHolder holder, T obj);

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView,parent,mLayoutRes,position);
		bindView(holder,mData.get(position));
		return holder.getItemView();
	}

	public void add(T data) {
		if (mData == null) {
			mData = new LinkedList<>();
		}
		mData.add(data);
		notifyDataSetChanged();
	}

	public void del() {
		if (mData != null && ! mData.isEmpty()) {
			mData.removeFirst();
		}
		notifyDataSetChanged();
	}

	public static class ViewHolder {

		private SparseArray<View> mViews; // 存储ListView 的 item中的View

		private View item; // 存放convertView

		private int position;

		private Context context;

		public ViewHolder(Context context, ViewGroup parent, int layoutRes) {
			mViews = new SparseArray<>();
			this.context = context;
			item = LayoutInflater.from(context).inflate(layoutRes,parent,false);
			item.setTag(this);
		}

		public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
									  int layoutRes, int position) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder(context, parent, layoutRes);
			} else {
				holder = (ViewHolder) convertView.getTag();
				holder.item = convertView;
			}
			holder.position = position;
			return holder;
		}

		public <E extends View> E getView(int id) {
			E t = (E) mViews.get(id);
			if (t == null) {
				t = item.findViewById(id);
				mViews.put(id, t);
			}
			return t;
		}

		public ViewHolder setText(int id, CharSequence text) {
			View view = getView(id);
			if (view instanceof TextView) {
				((TextView )view).setText(text);
			}
			return this;
		}

		public ViewHolder setImageResource(int id, int drawableRes) {
			View view = getView(id);
			if (view instanceof ImageView) {
				((ImageView) view).setImageResource(drawableRes);
			} else {
				view.setBackgroundResource(drawableRes);
			}
			return this;
		}

		public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
			getView(id).setOnClickListener(listener);
			return this;
		}

		public ViewHolder setTag(int id, Object tag) {
			getView(id).setTag(tag);
			return this;
		}

		public View getItemView() {
			return this.item;
		}
	}
}
