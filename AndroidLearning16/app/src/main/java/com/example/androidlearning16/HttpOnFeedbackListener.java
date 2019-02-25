package com.example.androidlearning16;

/**
 * Created by yaochao on 2019/02/23
 */
public interface HttpOnFeedbackListener {

	void onFinished(String response);

	void OnError(Throwable e);
}
