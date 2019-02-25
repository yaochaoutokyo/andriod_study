package com.example.weichat_demo.template;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.weichat_demo.utils.ActivityCollector;

/**
 * Created by yaochao on 2019/02/09
 */
public class MyActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.add(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.remove(this);
	}
}
