package com.example.andriodlearning13.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andriodlearning13.R;

/**
 * Created by yaochao on 2019/02/11
 */
public class StaticFragment extends Fragment {

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.i("Fragment", "onAttach()");
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Fragment", "onCreate()");
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.i("Fragment", "onViewCreated()");
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.static_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i("Fragment", "onActivityCreated()");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("Fragment", "onStart()");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("Fragment", "onResume()");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("Fragment", "onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("Fragment", "onStop()");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i("Fragment", "onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("Fragment", "onDestroy()");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i("Fragment", "onDetach()");
	}
}
