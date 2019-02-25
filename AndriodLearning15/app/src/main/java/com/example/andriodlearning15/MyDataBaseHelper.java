package com.example.andriodlearning15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yaochao on 2019/02/21
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

	private Context mContext;

	private static final String CREATE_TABLE = "create table book (" +
			"id integer primary key autoincrement, " +
			"author text, " +
			"name text, " +
			"price real )";

	public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
		Toast.makeText(mContext, "created table book", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
