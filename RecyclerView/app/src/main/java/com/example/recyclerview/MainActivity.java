package com.example.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;

	private RecyclerView.Adapter mAdapter;

	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_view);
		initData();
		initView();
	}

	private void initData() {
		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mAdapter = new MyAdapter(getData());
	}
	private void initView() {
		mRecyclerView = findViewById(R.id.my_recycler_view);
		// 设置布局管理器
		mRecyclerView.setLayoutManager(mLayoutManager);
		// 设置adapter
		mRecyclerView.setAdapter(mAdapter);
	}

	private ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<>();
		String temp = " item";
		for(int i = 0; i < 100; i++) {
			data.add(i + temp);
		}

		return data;
	}
}
