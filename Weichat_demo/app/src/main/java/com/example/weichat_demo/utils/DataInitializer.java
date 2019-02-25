package com.example.weichat_demo.utils;

import com.example.weichat_demo.domain.FriendDO;
import com.example.weichat_demo.domain.FriendVO;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yaochao on 2019/02/25
 */
public class DataInitializer {

	private static RealmResults<FriendDO> allFriendDOs;

	private static List<FriendVO> allFriendVOs;

	private static Realm realm;

	public static void getDataFromRealmDB() {
		realm = FriendRealmOp.getRealm();
		allFriendDOs = FriendRealmOp.selectAll(realm);
		for (int i = 0; i < allFriendDOs.size(); i++) {
			allFriendVOs.add(new FriendVO(allFriendDOs.get(i)));
		}
		realm.close();
	}

	public static void updataData() {
		getDataFromRealmDB();
	}
}
