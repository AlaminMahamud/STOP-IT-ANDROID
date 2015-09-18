package com.ethereal.stopit;

import java.io.IOException;
import java.net.MalformedURLException;
import com.ethereal1.stopit.R;
import com.facebook.android.Facebook;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends ActionBarActivity {
	private static String APP_ID = "906011589455834"; // Replace with your App
	// ID

	// Instance of Facebook Class
	private Facebook facebook = new Facebook(APP_ID);

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.createEvent) {
			Intent intent = new Intent(getBaseContext(),
					CreateEventActivity.class);
			startActivity(intent);
			return true;
		} else if (id == R.id.profile) {
			Intent intent = new Intent(getApplicationContext(),
					ProfileActivity.class);
			startActivity(intent);
			return true;
		} else if (id == R.id.logout) {
			Intent intent=new Intent(getBaseContext(),FacebookConnectActivity.class);
			startActivity(intent);
//			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
