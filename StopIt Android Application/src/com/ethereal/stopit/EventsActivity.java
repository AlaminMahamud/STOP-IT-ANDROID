package com.ethereal.stopit;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import com.ethereal.stopit.adapters.EventsActivityFragmentAdapter;
import com.ethereal1.stopit.R;



public class EventsActivity extends BaseActivity{
	ViewPager viewPager;
	EventsActivityFragmentAdapter adapter;
	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events);
		viewPager = (ViewPager) findViewById(R.id.pager);
		adapter =new EventsActivityFragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(1);	
	}
}
