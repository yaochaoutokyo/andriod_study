package com.example.andriodlearning11.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by yaochao on 2019/02/10
 */
public class NameContentProvider extends ContentProvider {

	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private DBOpenHelper dbOpenHelper;

	static{
		matcher.addURI("com.example.andriodlearning11.providers.myprovider", "test", 1);
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(this.getContext(), "test.db", null, 1);
		return true;
	}

	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
		return null;
	}

	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		return null;
	}

	@Nullable
	@Override
	public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
		switch(matcher.match(uri))
		{
			//把数据库打开放到里面是想证明uri匹配完成
			case 1:
				SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
				long rowId = db.insert("test", null, values);
				if(rowId > 0)
				{
					//在前面已有的Uri后面追加ID
					Uri nameUri = ContentUris.withAppendedId(uri, rowId);
					//通知数据已经发生改变
					getContext().getContentResolver().notifyChange(nameUri, null);
					return nameUri;
				}
		}
		return null;
	}

	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
		return 0;
	}
}
