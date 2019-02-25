package com.example.weichat_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.weichat_demo.R;

public class MomentsFragment extends Fragment {

	Context mContext;
	private WebView webView;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	public MomentsFragment() {
		// Required empty public constructor
	}

	public static MomentsFragment newInstance() {
		MomentsFragment fragment = new MomentsFragment();
		Bundle args = new Bundle();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.moments_fragment_layout, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
//		webView = view.findViewById(R.id.web_view_moments);
//		webView.getSettings().setJavaScriptEnabled(true);
//		webView.setWebViewClient(new WebViewClient());
//		webView.loadUrl("http://www.baidu.com/");
	}

}
