package com.ethereal.stopit;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.ethereal.stopit.objects.Event;
import com.ethereal.stopit.variables.ServerData;
import com.ethereal.stopit.variables.SharedPreferencesConstant;
import com.ethereal1.stopit.R;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateEventActivity extends BaseActivity {
	EditText eventName, corruptorName, workingPlace, thana, upzilla, district,
			description;
	Button btnCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);

		eventName = (EditText) findViewById(R.id.eventName);
		corruptorName = (EditText) findViewById(R.id.corruptorName);
		workingPlace = (EditText) findViewById(R.id.workingPlace);
		thana = (EditText) findViewById(R.id.thana);
		upzilla = (EditText) findViewById(R.id.upzilla);
		district = (EditText) findViewById(R.id.district);
		description = (EditText) findViewById(R.id.description);

		btnCreate = (Button) findViewById(R.id.buttonSubmit);

		btnCreate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new InsertTask().execute();
			}
		});
	}
	
	class InsertTask extends AsyncTask<Void, Void, Void>{
		int check=0;
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			SharedPreferences preferences = getSharedPreferences(
					SharedPreferencesConstant.MY_PREF, MODE_PRIVATE);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.EVEVT_NAME, eventName.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.CORRUPTOR_NAME, corruptorName.getText()
							.toString()));
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.WORKING_PLACE, workingPlace.getText()
							.toString()));
			nameValuePairs.add(new BasicNameValuePair(ServerData.THANA,
					thana.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair(ServerData.UPZILLA,
					upzilla.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair(ServerData.DICTRICT,
					district.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.DESCRIPTION, description.getText()
							.toString()));
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.PROOF_LINK, ""));
			nameValuePairs.add(new BasicNameValuePair(
					ServerData.CREATED_BY, preferences.getString(
							SharedPreferencesConstant.NAME, null)));

			JSONObject json = new JSONObject();
			JSONParser jsonParser = new JSONParser();
			json = jsonParser.makeHttpRequest(
					"http://ineedahelp.com/stopit/insert.php", "POST",
					nameValuePairs);
			try {
				int success = json.getInt("success");
				if (success == 1) {
					check=1;
				} else {
					check=0;
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
			if(check==1){
				Toast.makeText(getApplicationContext(), "insert successful", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(getApplicationContext(), "insert un-successful", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
}
