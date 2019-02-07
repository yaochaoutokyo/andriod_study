package com.example.andriodlearning3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button btnAdd, btnDel;
	private CompAdapter compAdapter;
	private LinkedList<Comp> comps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative_layout);

		comps = new LinkedList<>();
		ListView listView1 = findViewById(R.id.listview1);
		compAdapter = new CompAdapter(MainActivity.this, comps);
		TextView emptyView = findViewById(R.id.empty_textview);

		btnAdd = findViewById(R.id.btn_add);
		btnDel = findViewById(R.id.btn_del);
		btnAdd.setOnClickListener(this);
		btnDel.setOnClickListener(this);

		listView1.setEmptyView(emptyView);
		listView1.setAdapter(compAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_add : compAdapter.add();
								break;
			case R.id.btn_del : compAdapter.del();
								break;
			default: break;
		}
	}

	public void myClick(View view) {
		Toast.makeText(MainActivity.this, "by onClick", Toast.LENGTH_SHORT).show();
	}
}
