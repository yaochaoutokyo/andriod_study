package com.example.weichat_demo.tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yaochao on 2019/02/09
 */
public class BaseActivity extends AppCompatActivity {

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
