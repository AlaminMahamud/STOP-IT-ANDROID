package com.ethereal.stopit;

import com.ethereal.stopit.variables.SharedPreferencesConstant;
import com.ethereal1.stopit.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends BaseActivity {
	TextView name,location;
	ListView myListEvent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		name=(TextView) findViewById(R.id.name);
		location =(TextView) findViewById(R.id.location);
		
		myListEvent=(ListView) findViewById(R.id.myListEvent);
		
	    SharedPreferences preferences =getSharedPreferences(SharedPreferencesConstant.MY_PREF, Context.MODE_PRIVATE);
	    name.setText(preferences.getString(SharedPreferencesConstant.NAME, "problem"));
	}
}
