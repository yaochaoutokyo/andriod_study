package com.example.andriodlearning10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by yaochao on 2019/02/09
 */
public class MyBBReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "dynamic receiver from APP1", Toast.LENGTH_SHORT).show();
	}
}
