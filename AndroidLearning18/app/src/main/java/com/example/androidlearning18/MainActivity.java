package com.example.androidlearning18;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

	private Button btnSave, btnGet;

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);
		Realm.init(this);
		RealmConfiguration config = new RealmConfiguration.Builder().name("image_save_test.realm").build();
		Realm.setDefaultConfiguration(config);

		bindView();
	}

	private void bindView() {
		btnSave = findViewById(R.id.save_img);
		btnGet = findViewById(R.id.get_img);
		imageView = findViewById(R.id.image);

		btnSave.setOnClickListener((v) -> {
			Realm realm = Realm.getDefaultInstance();
			realm.beginTransaction();
			ImageDO imageDO = new ImageDO();
			imageDO.setId(1);
			imageDO.setImageBytes(bitmabToBytes(getApplicationContext()));
			realm.copyToRealmOrUpdate(imageDO);
			realm.commitTransaction();
			realm.close();
			Toast.makeText(getApplicationContext(), "insert image into realm", Toast.LENGTH_SHORT).show();
		});

		btnGet.setOnClickListener((v) -> {
			byte[] imageData = readImage();
			if (imageData != null) {
				Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
				imageView.setImageBitmap(image);
			} else {
				Toast.makeText(getApplicationContext(), "get image failed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public byte[] readImage(){
		Realm realm = Realm.getDefaultInstance();
		RealmResults<ImageDO> imageDOs = realm.where(ImageDO.class).equalTo("id", 1).findAll();
		byte[] imgData = null;
		if (! imageDOs.isEmpty()) {
			ImageDO imageDO = imageDOs.get(0);
			if (imageDO != null) {
				imgData = imageDO.getImageBytes();
			}
		}
		realm.close();
		return imgData;
	}

	public byte[] bitmabToBytes(Context context){
		//将图片转化为位图
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon9);
		int size = bitmap.getWidth() * bitmap.getHeight() * 4;
		//创建一个字节数组输出流,流的大小为size
		ByteArrayOutputStream baos= new ByteArrayOutputStream(size);
		try {
			//设置位图的压缩格式，质量为100%，并放入字节数组输出流中
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			//将字节数组输出流转化为字节数组byte[]
			byte[] imagedata = baos.toByteArray();
			return imagedata;
		}catch (Exception e){
		}finally {
			try {
				bitmap.recycle();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new byte[0];
	}
}
