package com.example.weichat_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.weichat_demo.R;
import com.example.weichat_demo.adapter.ContactRecycleViewAdapter;
import com.example.weichat_demo.domain.FriendDO;
import com.example.weichat_demo.entity.AppInfo;
import com.example.weichat_demo.entity.ContactInfo;
import com.example.weichat_demo.utils.BitmapTransferUtil;
import com.example.weichat_demo.utils.FriendRealmOp;
import com.example.weichat_demo.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ContactFragment extends Fragment {

	private Context mContext;
	private LinkedList<ContactInfo> contactInfoList;
	private Button btnSendHttpReq;
	private Realm realm;

	public ContactFragment() {
		contactInfoList = new LinkedList<>();
	}
	public static ContactFragment newInstance() {
		ContactFragment fragment = new ContactFragment();
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.contact_fragment_layout, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initData();
		RecyclerView recyclerView = view.findViewById(R.id.contact_recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		recyclerView.setAdapter(new ContactRecycleViewAdapter(contactInfoList));

		btnSendHttpReq = view.findViewById(R.id.btn_send_http);
		btnSendHttpReq.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HttpUtil.sendHttpRequest("http://192.168.56.1/get_data.json", new Callback() {
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

	private void initData() {
		realm = FriendRealmOp.getRealm();
		RealmResults<FriendDO> results = FriendRealmOp.selectAll(realm);
		for (int i = 0; i < results.size(); i++) {
			ContactInfo info = new ContactInfo();
			info.setContactName(results.get(i).getName());
			info.setContactEmail(results.get(i).getEmail());
			if (results.get(i).getPhoneNumber() != null) {
				info.setContactPhone(results.get(i).getPhoneNumber().toString());
			}
			info.setContactBitmap(BitmapTransferUtil.BytesToBitmap(results.get(i).getIconBytes()));
			contactInfoList.add(info);
		}
		realm.close();
	}

	private void parseWithGson(String json) {
		List<AppInfo> appInfos = new Gson().fromJson(json, new TypeToken<List<AppInfo>>(){}.getType());
		for (AppInfo app : appInfos) {
			Log.d("main3", app.toString());
		}
	}
}
