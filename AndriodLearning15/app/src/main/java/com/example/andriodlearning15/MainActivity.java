package com.example.andriodlearning15;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private EditText editText, editText2;
	private String inputText;
	private String savedText;
	private Button create, add, edit, find, del;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindView();

		savedText = readData();
		if (! TextUtils.isEmpty(savedText)) {
			editText.setText(readData());
			Toast.makeText(this, "restored data", Toast.LENGTH_SHORT).show();
		}


		if (readDate2().length() > 1) {
			editText2.setText(readDate2());
			Toast.makeText(this, "restored data2", Toast.LENGTH_SHORT).show();
		}
	}

	private void bindView() {
		editText = findViewById(R.id.edit_text);
		editText2 = findViewById(R.id.edit_text2);

		create = findViewById(R.id.create_db);
		create.setOnClickListener(this);
		add = findViewById(R.id.insert_data);
		add.setOnClickListener(this);
		edit = findViewById(R.id.update_data);
		edit.setOnClickListener(this);
		find = findViewById(R.id.query_data);
		find.setOnClickListener(this);
		del = findViewById(R.id.delete_data);
		del.setOnClickListener(this);

		dbHelper = new MyDataBaseHelper(this, "bookDB", null, 1);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.create_db:
				db = dbHelper.getWritableDatabase();
				break;
			case R.id.insert_data:
				db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("author", "yaochao");
				values.put("name", "air-lift pump");
				values.put("price", 50.5);
				Long result = db.insert("book",null, values);
				if (result == -1) {
					Toast.makeText(this, "insert failed", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "insert book " + values.getAsString("name"), Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.query_data:
				db = dbHelper.getWritableDatabase();
				Cursor cursor = db.query("book", new String[] {"name", "price"}, null,
						null, null, null, null);
				if (cursor.moveToFirst()) {
					do {
						String name = cursor.getString(cursor.getColumnIndex("name"));
						Long price = cursor.getLong(cursor.getColumnIndex("price"));
						Log.d("book-query", "name: " + name + ", price: " + price);
					} while (cursor.moveToNext());
				}
				cursor.close();
				break;
			case R.id.update_data:
				db = dbHelper.getWritableDatabase();
				ContentValues values2 = new ContentValues();
				values2.put("price", 45.2);
				db.update("book",values2, "author = ? and name = ?",
						new String[] {"yaochao", "air-lift pump"});
				Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
				break;
			case R.id.delete_data:
				db = dbHelper.getWritableDatabase();
				db.delete("book", "author = ?", new String[] {"yao"});
				Toast.makeText(this, "deleted book by yao", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		inputText = editText.getText().toString();
		writeData();
		writeDate2();
	}

	private void writeData() {
		FileOutputStream out = null;
		BufferedWriter writer = null;

		try {
			out = openFileOutput("data", Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(inputText);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {

						writer.close();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	private String readData() {
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();

		try {
			in = openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return builder.toString();
	}

	private void writeDate2() {
		SharedPreferences sp = getSharedPreferences("data2", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("editText2",editText2.getText().toString());
		editor.apply();
	}

	private String readDate2() {
		return getSharedPreferences("data2", MODE_PRIVATE).getString("editText2","");
	}
}
