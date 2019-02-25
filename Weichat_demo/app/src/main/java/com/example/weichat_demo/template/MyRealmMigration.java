package com.example.weichat_demo.template;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by yaochao on 2019/02/25
 */
public class MyRealmMigration implements RealmMigration {

	@Override
	public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
		RealmSchema schema = realm.getSchema();

		if (oldVersion == 0) {
			final RealmObjectSchema friendSchema = schema.get("FriendDO");
			friendSchema.removeField("phoneNumber").addField("phoneNumber",String.class);
		}
	}
}
