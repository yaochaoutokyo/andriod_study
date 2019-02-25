package com.example.androidlearning16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity {

	private Button getJsonHc, getJsonOk;
	private final String ADDRESS = "http://192.168.56.1/get_data.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main3_activity);
		bindView();
	}

	private void bindView() {
		getJsonHc = findViewById(R.id.button4);
		getJsonHc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("main3", "button4");
				HttpUtils.sendRequestWithHttpClient(ADDRESS, new HttpOnFeedbackListener() {
					@Override
					public void onFinished(String response) {
						parseWithJsonObject(response);
					}

					@Override
					public void OnError(Throwable e) {
						e.printStackTrace();
					}
				});
			}
		});

		getJsonOk = findViewById(R.id.button5);
		getJsonOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("main3", "button5");
				HttpUtils.sendRequestWithOkHttp(ADDRESS, new Callback() {
					@Override
					public void onFailure(Call call, IOException e) {
						e.printStackTrace();
					}

					@Override
					public void onResponse(Call call, Response response) throws IOException {
						parseWithGson(response.body().string());
					}
				});
			}
		});
	}

	private void parseWithJsonObject(String json) {
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String id = jsonObject.getString("id");
				String name = jsonObject.getString("name");
				String version = jsonObject.getString("version");
				AppInfo appInfo = new AppInfo(id, name, version);
				Log.d("main3", appInfo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseWithGson(String json) {
		List<AppInfo> appInfos = new Gson().fromJson(json, new TypeToken<List<AppInfo>>(){}.getType());
		for (AppInfo app : appInfos) {
			Log.d("main3", app.toString());
		}
	}
}
