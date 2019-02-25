package com.example.weichat_demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weichat_demo.LoginActivity;
import com.example.weichat_demo.R;
import com.example.weichat_demo.entity.User;
import com.example.weichat_demo.utils.UserCollecter;

/**
 * Created by yaochao on 2019/02/12
 */
public class AccountFragment extends Fragment {

	private TextView accountInfo;
	private Button btnLogout;
	private Context mContext;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.account_fragment_layout, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);


		// 账户信息与登出功能
		accountInfo = view.findViewById(R.id.textview_current_account_info);
		User currentUser = UserCollecter.getCurrentUser();
		if (currentUser != null) {
			accountInfo.setText(currentUser.getAccount());
		}

		btnLogout = view.findViewById(R.id.button_logout);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UserCollecter.removeCurrentUser();
				Intent intent = new Intent(mContext, LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
				Toast.makeText(mContext, "logout successfully", Toast.LENGTH_SHORT).show();
				getActivity().finish();
			}
		});
	}
}
