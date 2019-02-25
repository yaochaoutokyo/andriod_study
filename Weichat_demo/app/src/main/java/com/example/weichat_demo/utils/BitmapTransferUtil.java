package com.example.weichat_demo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by yaochao on 2019/02/24
 */
public class BitmapTransferUtil {

	public static byte[] BitmapToBytes(Bitmap bitmap) {
		if (bitmap != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			return baos.toByteArray();
		}
		return new byte[0];
	}

	public static Bitmap BytesToBitmap(byte[] bytes) {
		if (bytes != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			return bitmap;
		}
		return null;
	}
}
