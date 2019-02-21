package com.example.androidlearning14;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yaochao on 2019/02/12
 */
public class ContentFragmentAdapter extends FragmentPagerAdapter {

	private final int PAGE_COUNT = 4;
	private ContentFragment messageFrag, contactFrag, momentsFrag, accountFrag;

	public ContentFragmentAdapter(FragmentManager fm) {
		super(fm);
		messageFrag = ContentFragment.newInstance("Message", "message from your friends");
		contactFrag = ContentFragment.newInstance("Contact", "your contact list");
		momentsFrag = ContentFragment.newInstance("Moments", "share your moments with your friends");
		accountFrag = ContentFragment.newInstance("Account", "personalize your account");
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		switch (position) {
			case 0:
				fragment = messageFrag;
				break;
			case 1:
				fragment = contactFrag;
				break;
			case 2:
				fragment = momentsFrag;
				break;
			case 3:
				fragment = accountFrag;
				break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}
}
