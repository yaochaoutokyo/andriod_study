package com.example.weichat_demo.utils;

import android.widget.Toast;

import com.example.weichat_demo.domain.FriendDO;
import com.example.weichat_demo.template.MyApplication;
import com.example.weichat_demo.template.MyRealmMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by yaochao on 2019/02/24
 */
public class FriendRealmOp {

	private static Realm realm;

	private static RealmConfiguration config = new RealmConfiguration.Builder()
			.name("Friend.realm")
			.schemaVersion(1) // Must be bumped when the schema changes
			.migration(new MyRealmMigration()) // Migration to run instead of throwing an exception
			.build();

	private static RealmConfiguration oldConfig = new RealmConfiguration.Builder()
			.name("Friend.realm").build();

	public static Realm getRealm() {
		return Realm.getInstance(config);
	}

	public static void insert(FriendDO friendDO) {
		realm = Realm.getInstance(config);
		realm.executeTransactionAsync(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				Number maxId = realm.where(FriendDO.class).max("id");
				int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
				friendDO.setId(nextId);
				realm.copyToRealm(friendDO);
			}
		});
		realm.close();
	}

	public static void update(FriendDO friendDO) {
		realm = Realm.getInstance(config);
		realm.executeTransactionAsync(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				RealmResults<FriendDO> result = realm.where(FriendDO.class).equalTo("id",
						friendDO.getId()).findAll();
				if (! result.isEmpty()) {
					if (friendDO.getBestFriendId() == null) {
						friendDO.setBestFriendId(result.get(0).getBestFriendId());
					}
					if (friendDO.getName() == null) {
						friendDO.setName(result.get(0).getName());
					}
					if (friendDO.getEmail() == null) {
						friendDO.setEmail(result.get(0).getEmail());
					}
					if (friendDO.getIconBytes() == null) {
						friendDO.setIconBytes(result.get(0).getIconBytes());
					}
					if (friendDO.getSays() == null) {
						friendDO.setSays(result.get(0).getSays());
					}
					if (friendDO.getPhoneNumber() == null) {
						friendDO.setPhoneNumber(result.get(0).getPhoneNumber());
					}
					realm.copyToRealmOrUpdate(friendDO);
				}
			}
		});
		realm.close();
	}

	public static RealmResults<FriendDO> selectById(int id, Realm vRealm) {
		RealmResults<FriendDO> friendDOs = vRealm.where(FriendDO.class).equalTo("id", id).findAll();
		return friendDOs;
	}

	public static RealmResults<FriendDO> selectAll(Realm vRealm) {
		RealmResults<FriendDO> friendDOs = vRealm.where(FriendDO.class).findAll();
		return friendDOs;
	}
}
