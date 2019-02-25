package com.example.weichat_demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.weichat_demo.fragment.AccountFragment;
import com.example.weichat_demo.fragment.ContactFragment;
import com.example.weichat_demo.fragment.ContentFragment;
import com.example.weichat_demo.fragment.MessageFragment;
import com.example.weichat_demo.fragment.MomentsFragment;

/**
 * Created by yaochao on 2019/02/12
 */
@Deprecated
public class ContentFragmentAdapter extends FragmentPagerAdapter {

	private final int PAGE_COUNT = 4;
	private MomentsFragment momentsFrag;
	private AccountFragment accountFrag;
	private MessageFragment messageFrag;
	private ContactFragment contactFrag;

	public ContentFragmentAdapter(FragmentManager fm) {
		super(fm);
		messageFrag = new MessageFragment();
		contactFrag = new ContactFragment();
		momentsFrag = new MomentsFragment();
		accountFrag = new AccountFragment();
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
