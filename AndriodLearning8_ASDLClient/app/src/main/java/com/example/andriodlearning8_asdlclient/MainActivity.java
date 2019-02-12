package com.example.andriodlearning8_asdlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andriodlearning8.IPerson;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	private IPerson binder;

	private PersonConnection personConnection = new PersonConnection();

	private Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Intent intent = new Intent("person_aidl_service");
		intent.setPackage("com.example.andriodlearning8");
		bindService(intent, personConnection, BIND_AUTO_CREATE);

		Button button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Toast.makeText(MainActivity.this, binder.queryPerson(random.nextInt(5)),
							Toast.LENGTH_SHORT).show();
				} catch (RemoteException e) {
					throw new RuntimeException(e);
				}
			}
		});
    }

    private final class PersonConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("test", "onServiceConnected()");
			binder = IPerson.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			binder = null;
		}
	}
}
