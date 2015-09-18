package com.ethereal.stopit;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ethereal.stopit.objects.Event;
import com.ethereal.stopit.variables.ServerData;
import com.ethereal.stopit.variables.SharedPreferencesConstant;
import com.ethereal1.stopit.R;

public class EventDescriptionActivity extends BaseActivity {
	Event event;
	TextView eventName, corruptorName, workingPlace, address, voteYes, voteNo,
			description, proofLink, createdBy;
	Button btnCorrupt, btnNonCorrupt;
	String ANDROID_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_description);
		event = (Event) getIntent().getSerializableExtra("event");

		btnCorrupt = (Button) findViewById(R.id.buttonCorrupt);
		btnNonCorrupt = (Button) findViewById(R.id.buttonNonCorrupt);
		eventName = (TextView) findViewById(R.id.eventName);
		corruptorName = (TextView) findViewById(R.id.corruptorName);
		workingPlace = (TextView) findViewById(R.id.workingPlace);
		address = (TextView) findViewById(R.id.address);
		voteYes = (TextView) findViewById(R.id.voteYes);
		voteNo = (TextView) findViewById(R.id.voteNo);
		description = (TextView) findViewById(R.id.description);
		proofLink = (TextView) findViewById(R.id.proofLink);
		createdBy = (TextView) findViewById(R.id.createdBy);

		ANDROID_ID = Secure.getString(getBaseContext().getContentResolver(),
				Secure.ANDROID_ID);
		Toast.makeText(getApplicationContext(), ANDROID_ID, Toast.LENGTH_SHORT)
				.show();

		eventName.setText(event.getEventName());
		corruptorName.setText(event.getName());
		workingPlace.setText(event.getWorkingPlace());
		address.setText(event.getThana() + "," + event.getUpzilla() + ","
				+ event.getDistrict() + ".");
		voteYes.setText(event.getVoteYes() + "");
		voteNo.setText(event.getVoteNo() + "");
		description.setText(event.getDescription());
		proofLink.setText(event.getProofLink());
		createdBy.setText(event.getCreated_by());

		btnCorrupt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UpdateYes().execute();
			}
		});

		btnNonCorrupt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UpdateNo().execute();
			}
		});

	}

	class UpdateYes extends AsyncTask<Void, Void, Void> {
		int check = 0;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			SharedPreferences preferences = getSharedPreferences(
					SharedPreferencesConstant.MY_PREF, MODE_PRIVATE);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.CORRUPTOR_NAME, preferences.getString(
							SharedPreferencesConstant.NAME, null)));
			nameValuePairs.add(new BasicNameValuePair(ServerData.USER_ID,
					preferences.getString(SharedPreferencesConstant.ID, null)));
			nameValuePairs.add(new BasicNameValuePair(ServerData.EVENT_ID,
					event.getId() + ""));
			nameValuePairs.add(new BasicNameValuePair(ServerData.VOTE_STATUS,
					"yes"));
			nameValuePairs.add(new BasicNameValuePair(ServerData.DEVICE_ID,
					ANDROID_ID));

			JSONObject json = new JSONObject();
			JSONParser jsonParser = new JSONParser();
			json = jsonParser.makeHttpRequest(
					"http://ineedahelp.com/stopit/updateyes.php", "POST",
					nameValuePairs);
			try {
				int success = json.getInt("success");
				if (success == 1) {
					check = 1;
				} else {
					check = 0;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (check == 1) {
				Toast.makeText(getApplicationContext(), "update successful",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "update un-successful",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	class UpdateNo extends AsyncTask<Void, Void, Void> {
		int check = 0;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			SharedPreferences preferences = getSharedPreferences(
					SharedPreferencesConstant.MY_PREF, MODE_PRIVATE);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.CORRUPTOR_NAME, preferences.getString(
							SharedPreferencesConstant.NAME, null)));
			nameValuePairs.add(new BasicNameValuePair(ServerData.USER_ID,
					preferences.getString(SharedPreferencesConstant.ID, null)));
			nameValuePairs.add(new BasicNameValuePair(ServerData.EVENT_ID,
					event.getId() + ""));
			nameValuePairs.add(new BasicNameValuePair(ServerData.VOTE_STATUS,
					"no"));
			nameValuePairs.add(new BasicNameValuePair(ServerData.DEVICE_ID,
					ANDROID_ID));

			JSONObject json = new JSONObject();
			JSONParser jsonParser = new JSONParser();
			json = jsonParser.makeHttpRequest(
					"http://ineedahelp.com/stopit/updateno.php", "POST",
					nameValuePairs);
			try {
				int success = json.getInt("success");
				if (success == 1) {
					check = 1;
				} else {
					check = 0;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (check == 1) {
				Toast.makeText(getApplicationContext(), "update successful",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "update un-successful",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
