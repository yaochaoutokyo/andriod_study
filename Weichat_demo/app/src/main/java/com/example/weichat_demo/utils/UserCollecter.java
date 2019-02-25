package com.example.weichat_demo.utils;

import com.example.weichat_demo.entity.User;

import java.util.ArrayList;

/**
 * Created by yaochao on 2019/02/09
 */
public class UserCollecter {

	private static ArrayList<User> userArrayList = new ArrayList<>();

	private static User currentUser;

	public static void add(User user) {
		userArrayList.add(user);
	}

	public static void setCurrentUser(String account, String password) {
		currentUser = new User(account, password);
	}

	public static void removeCurrentUser() {
		currentUser = new User("", "");
	}
	public static User getCurrentUser() {
		return currentUser;
	}

	public static boolean contains(User user) {
		for (User userInList : userArrayList) {
			if (userInList.getAccount().equals(user.getAccount()) &&
				userInList.getPasswrod().equals(user.getPasswrod())) {
				return true;
			}
		}
		return false;
	}
}
