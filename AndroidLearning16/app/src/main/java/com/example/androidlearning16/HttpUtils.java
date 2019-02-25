package com.example.androidlearning16;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yaochao on 2019/02/23
 */
public class HttpUtils {

	public static void sendRequestWithHttpClient(final String address, final HttpOnFeedbackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection conn = null;
				BufferedReader reader = null;
				try {
					URL url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);
					InputStream in = conn.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					if (listener != null) {
						listener.onFinished(builder.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.OnError(e);
					}
				} finally {
					if (conn != null) {
						conn.disconnect();
					}
				}
			}
		}).start();
	}

	public static void sendRequestWithOkHttp(String address, Callback callback) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(address).build();
		client.newCall(request).enqueue(callback);
	}
}
