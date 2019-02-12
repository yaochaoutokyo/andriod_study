package com.example.andriodlearning12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andriodlearning12.entity.Book;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private Button send, send1, send2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final MyApp myApp = (MyApp) getApplication();

		bindView();

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Book book = new Book("Thinking in Java", "Bruce Eckel");
				String bookJson = new Gson().toJson(book);
				Intent intent = new Intent(MainActivity.this, ReceiverActivity.class);
				intent.putExtra("bookJson", bookJson);
				startActivity(intent);
			}
		});

		send1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<String> books = new ArrayList<>();
				books.add("Thinking in Java");
				books.add("First line in Andriod");
				Intent intent = new Intent(MainActivity.this, ReceiverActivity.class);
				intent.putStringArrayListExtra("booksJson", books);
				startActivity(intent);
			}
		});

		send2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myApp.setName("yaochao");
				Intent intent = new Intent(MainActivity.this, ReceiverActivity.class);
				startActivity(intent);
			}
		});
	}

	void bindView() {
		send = findViewById(R.id.button1);
		send1 = findViewById(R.id.button2);
		send2 = findViewById(R.id.button3);
	}
}
