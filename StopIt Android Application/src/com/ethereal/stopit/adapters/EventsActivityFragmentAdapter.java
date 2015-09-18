package com.ethereal.stopit.adapters;

import com.ethereal.stopit.fragments.DistrictEventFragment;
import com.ethereal.stopit.fragments.MostRecentEventFragment;
import com.ethereal.stopit.fragments.MostVotedEventFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class EventsActivityFragmentAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 3;
	// Tab Titles
	private String tabtitles[] = new String[] { "District Events",
			"Most Voted Events", "Most Recent Events" };
	Context context;

	public EventsActivityFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			DistrictEventFragment districtEventFragment = new DistrictEventFragment();
			return districtEventFragment;
		case 1:
			MostVotedEventFragment mostVotedEventFragment = new MostVotedEventFragment();
			return mostVotedEventFragment;

		case 2:
			MostRecentEventFragment mostRecentEventFragment = new MostRecentEventFragment();
			return mostRecentEventFragment;
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabtitles[position];
	}
}