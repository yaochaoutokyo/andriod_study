package com.example.andriodlearning11;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	Button btnAddContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		bindView();

		btnAddContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				try {
//					addContact();
//				} catch (Exception e) {
//					Toast.makeText(MainActivity.this, "failed to add new contact", Toast.LENGTH_SHORT).show();
//					e.printStackTrace();
//				}
				Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.setType("image/*");
				startActivityForResult(intent, 0x123);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0x123 && resultCode == Activity.RESULT_OK) {
			Uri uri;
			if (data != null) {
				uri = data.getData();
				Log.e("Test", "Uri: " + uri.toString());
			}
		}
	}


	void bindView() {
		btnAddContact = findViewById(R.id.button);
	}

	public void addContact() throws RemoteException, OperationApplicationException {
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");

		ContentResolver resolver = getContentResolver();

		ArrayList<ContentProviderOperation> operations = new ArrayList<>();

		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
				.withValue("acount_name", null)
				.build();
		operations.add(op1);

		ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/name")
				.withValue("data2", "Coder-pig")
				.build();
		operations.add(op2);

		ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/phone_v2")
				.withValue("data1", "13798988888")
				.withValue("data2", "2")
				.build();
		operations.add(op3);

		ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
				.withValue("mimetype", "vnd.android.cursor.item/email_v2")
				.withValue("data1", "779878443@qq.com")
				.withValue("data2", "2")
				.build();
		operations.add(op4);

		resolver.applyBatch("com.android.contacts", operations);
		Toast.makeText(MainActivity.this, "added new contact", Toast.LENGTH_SHORT).show();
	}
}
