package com.example.andriodlearning12.entity;

import java.util.HashMap;

/**
 * Created by yaochao on 2019/02/10
 */
public class GlobalSingleton {

	private static GlobalSingleton instance = null;

	private final HashMap<String, Object> hashMap;

	public synchronized static GlobalSingleton getInstance() {
		if (instance == null) {
			instance = new GlobalSingleton();
		}
		return instance;
	}

	public GlobalSingleton() {
		hashMap = new HashMap<>();
	}

	public void put(String str, Object obj) {
		hashMap.put(str, obj);
	}

	public Object get(String str) {
		return hashMap.get(str);
	}
}
