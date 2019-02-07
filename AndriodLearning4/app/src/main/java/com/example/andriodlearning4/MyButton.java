package com.example.andriodlearning4;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by yaochao on 2019/02/05
 */
public class MyButton extends AppCompatButton {
	private static String tag = "test";
	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i(tag,"onKeyDown");
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.i(tag,"onKeyUp");
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i(tag, "onTouch(Defined)");
		return false;
	}


}
