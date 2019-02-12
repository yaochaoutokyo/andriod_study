package com.example.andriodlearning13.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.andriodlearning13.R;

/**
 * Created by yaochao on 2019/02/11
 */
public class DynamicFragment2 extends Fragment {

	Button btnDf2;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dynamic_fragment_2, container, false);
		btnDf2 = view.findViewById(R.id.btn_df2);
		btnDf2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.root, new DynamicFragment1(), "DyFrag1")
						.addToBackStack(null)
						.commit();
			}
		});
		return view;
	}
}
