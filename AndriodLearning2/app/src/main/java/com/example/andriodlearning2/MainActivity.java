package com.example.andriodlearning2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

	private String[] strings1, strings = {"abandon", "abnormal", "absorb", "absent"};
	private List<String> stringList;
	private Button button1;
	private Random rand;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative_layout);

		ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this,
				R.array.strs1, android.R.layout.simple_list_item_multiple_choice);
		ListView listView1 = findViewById(R.id.listview1);
		listView1.setAdapter(arrayAdapter2);


		stringList = Arrays.asList(strings);
		ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(getApplicationContext(),
				android.R.layout.simple_list_item_multiple_choice, stringList);
		ListView listView2 = findViewById(R.id.listview2);
		listView2.setAdapter(arrayAdapter1);
		strings1 = getResources().getStringArray(R.array.strs1);
		rand = new Random();
		button1 = findViewById(R.id.btn1);
//		button1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				stringList.add(strings1[rand.nextInt(strings1.length)]);
//			}
//		});

	}
}
