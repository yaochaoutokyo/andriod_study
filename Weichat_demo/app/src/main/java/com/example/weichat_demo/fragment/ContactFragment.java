package com.example.weichat_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weichat_demo.R;
import com.example.weichat_demo.adapter.ContactRecycleViewAdapter;
import com.example.weichat_demo.entity.ContactInfo;

import java.util.LinkedList;

public class ContactFragment extends Fragment {

	private Context mContext;
	private LinkedList<ContactInfo> contactInfoList;

	public ContactFragment() {
		contactInfoList = new LinkedList<>();
	}
	public static ContactFragment newInstance() {
		ContactFragment fragment = new ContactFragment();
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.contact_fragment_layout, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initData();
		RecyclerView recyclerView = view.findViewById(R.id.contact_recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		recyclerView.setAdapter(new ContactRecycleViewAdapter(contactInfoList));
	}

	private void initData() {
		contactInfoList.add(new ContactInfo(R.mipmap.icon1,"Tom", "08088888888", "tom@weichat.com" ));
		contactInfoList.add(new ContactInfo(R.mipmap.icon2,"Jerry", "07077777777", "jerry@weichat.com" ));
		contactInfoList.add(new ContactInfo(R.mipmap.icon3,"Pick", "09098791353", "pick@weichat.com" ));
		contactInfoList.add(new ContactInfo(R.mipmap.icon4,"Lua", "08088818872", "lua@weichat.com" ));
	}
}
