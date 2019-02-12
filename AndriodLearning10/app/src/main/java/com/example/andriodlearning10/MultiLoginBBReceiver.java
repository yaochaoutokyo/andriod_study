package com.example.andriodlearning10;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

/**
 * Created by yaochao on 2019/02/09
 */
public class MultiLoginBBReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Notice");
		builder.setMessage("you account is signed in in else where, please login again!");
		builder.setCancelable(false);
		builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityCollector.finishAll();
				Intent intentToLogin = new Intent(context, LoginActivity.class);
				intentToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intentToLogin);
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}
}
