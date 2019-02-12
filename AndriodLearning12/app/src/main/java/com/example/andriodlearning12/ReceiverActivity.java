package com.example.andriodlearning12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andriodlearning12.entity.Book;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class ReceiverActivity extends AppCompatActivity {

	private Button show, show1, show2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final MyApp myApp = (MyApp) getApplication();
		setContentView(R.layout.activity_receiver);

		bindView();

		show.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String bookJson = getIntent().getStringExtra("bookJson");
				Book book = new Gson().fromJson(bookJson, Book.class);
				Toast.makeText(ReceiverActivity.this, book.getTitle() + " by " +
						book.getAuthor(), Toast.LENGTH_SHORT).show();
				Log.i("bookJson", bookJson);
			}
		});

		show1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<String> books = getIntent().getStringArrayListExtra("booksJson");
				Toast.makeText(ReceiverActivity.this, books.get(0), Toast.LENGTH_SHORT).show();
				Log.i("booksJson", books.toString());
			}
		});

		show2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ReceiverActivity.this, myApp.getName(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	void bindView() {
		show = findViewById(R.id.button4);
		show1 = findViewById(R.id.button5);
		show2 = findViewById(R.id.button6);
	}
}
