package com.example.andriodlearning7;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by yaochao on 2019/02/06
 */
public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

	private TextView textView;

	private ProgressBar progressBar;

	private Button button;

	private boolean canceled = false;

	public MyAsyncTask(TextView textView, ProgressBar progressBar, Button button) {
		super();
		this.progressBar = progressBar;
		this.textView = textView;
		this.button = button;
	}

	@Override
	protected void onPreExecute() {
		canceled = false;
		textView.setText("downloading...");
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		progressBar.setProgress(values[0]);
		textView.setText("progress: " + values[0] + "/100");
		if (progressBar.getProgress() == 100) {
			textView.setText("completed");
			button.setText("finish");
		}
	}

	@Override
	protected void onCancelled() {
		textView.setText("stopped");
		progressBar.setProgress(0);
		canceled = true;
	}

	@Override
	protected String doInBackground(Integer... integers) {
		int i = 0;
		for (i = 10; i <= 100; i = i + 10) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!canceled) {
					publishProgress(i);
				} else {
					return "";
				}

		}
		return "finished";
	}
}
