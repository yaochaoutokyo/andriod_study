package com.example.andriodlearning5;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	public static final String UPPER_NUM = "upper";

	private int[] imgIds = {R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4,
					R.mipmap.icon5, R.mipmap.icon6};

	private int start = 0;

	private ImageView imageView;

	private EditText editText;

	private NewThread newThread;

	private Handler myHandler = new Handler()
	{
		@Override
		//重写handleMessage方法,根据msg中what的值判断是否执行后续操作
		public void handleMessage(Message msg) {
			if(msg.what == 0x123 && imageView != null)
			{
				imageView.setImageResource(imgIds[start++ % imgIds.length]);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anime);

		imageView = findViewById(R.id.imageview1);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				myHandler.sendEmptyMessage(0x123);
			}
		},0,1000);

		editText = findViewById(R.id.edittext1);
		newThread = new NewThread();
		newThread.start();
	}

	public void sendFirstDigital(View view) {
		Bundle bundle = new Bundle();
		Message message = new Message();
		bundle.putInt(UPPER_NUM, Integer.parseInt(editText.getText().toString()));
		message.what = 0x111;
		message.setData(bundle);
		newThread.handler.sendMessage(message);
	}

	class NewThread extends Thread {

		public Handler handler;

		public void run() {
			Looper.prepare();
			handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 0x111) {
						int upper = msg.getData().getInt(UPPER_NUM);
						String[] strings = Integer.toString(upper).split("");
						Toast.makeText(MainActivity.this, strings[1],Toast.LENGTH_SHORT).show();
					}
				}
			};
			Looper.loop();
		}
	}

}
