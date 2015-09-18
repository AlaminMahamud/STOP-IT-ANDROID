package com.ethereal.stopit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.ethereal.stopit.variables.SharedPreferencesConstant;
import com.ethereal1.stopit.R;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class FacebookConnectActivity extends ActionBarActivity {

	// Your Facebook APP ID
	private static String APP_ID = "906011589455834"; // Replace with your App
														// ID

	// Instance of Facebook Class
	private Facebook facebook = new Facebook(APP_ID);
	private AsyncFacebookRunner mAsyncRunner;
	private SharedPreferences mPrefs;

	// Buttons
	Button btnFbLogin, btnLogout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook_connect);

		mPrefs = getSharedPreferences(SharedPreferencesConstant.MY_PREF,
				MODE_PRIVATE);

		btnFbLogin = (Button) findViewById(R.id.btn_fblogin);
		btnLogout = (Button) findViewById(R.id.btn_get_profile);
		btnLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				logoutFromFacebook();
			}
		});
		mAsyncRunner = new AsyncFacebookRunner(facebook);

		/**
		 * Login button Click event
		 * */

		//checkLogin();
		btnFbLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				loginToFacebook();
			}
		});
	}

	private void checkLogin() {
		// TODO Auto-generated method stub
		final SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);
		String access_token = mPreferences.getString("access_token", null);
		long expires = mPreferences.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);

			Intent intent = new Intent(FacebookConnectActivity.this,
					EventsActivity.class);
			startActivity(intent);
		}
	}

	/**
	 * Function to login into facebook
	 * */
	public void loginToFacebook() {
		final SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);
		String access_token = mPreferences.getString("access_token", null);
		long expires = mPreferences.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);

			 Intent intent = new Intent(FacebookConnectActivity.this,
			 EventsActivity.class);
			 startActivity(intent);
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}


		if (!facebook.isSessionValid()) {
			facebook.authorize(this, new String[] { "email" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPreferences
									.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							getProfileInformation();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}

	/**
	 * Get Profile information by making request to Facebook Graph API
	 * */
	public void getProfileInformation() {
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);

					// getting name of the user
					final String name = profile.getString("name");
					final String id = profile.getString("id");

					SharedPreferences.Editor editor = mPrefs.edit();
					editor.putString(SharedPreferencesConstant.NAME, name);
					editor.putString(SharedPreferencesConstant.ID, id);
					editor.commit();

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Name: " + name + "\nid: " + id,
									Toast.LENGTH_LONG).show();
						}

					});

				} catch (JSONException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(FacebookConnectActivity.this,
						EventsActivity.class);
				startActivity(intent);
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}

	/**
	 * Function to Logout user from Facebook
	 * */
	public void logoutFromFacebook() {

		AsyncTask<Void, Void, Void> logoutFb = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					facebook.logout(getApplicationContext());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				final SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);
				SharedPreferences.Editor editor = mPreferences
						.edit();
				editor.putString("access_token",
						null);
				editor.putLong("access_expires",
						0);
				editor.commit();
				Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_SHORT).show();
			}
		};
		logoutFb.execute();
		// mAsyncRunner.logout(this, new RequestListener() {
		// @Override
		// public void onComplete(String response, Object state) {
		// Log.d("Logout from Facebook", response);
		// if (Boolean.parseBoolean(response) == true) {
		// runOnUiThread(new Runnable() {
		//
		// @Override
		// public void run() {
		// // make Login button visible
		// SharedPreferences.Editor editor = mPrefs.edit();
		// editor.putString("access_token", null);
		// editor.putLong("access_expires", 0);
		// editor.commit();
		// Toast.makeText(getApplicationContext(), "logout",
		// Toast.LENGTH_SHORT).show();
		// }
		//
		// });
		//
		// }
		// }
		//
		// @Override
		// public void onIOException(IOException e, Object state) {
		// }
		//
		// @Override
		// public void onFileNotFoundException(FileNotFoundException e,
		// Object state) {
		// }
		//
		// @Override
		// public void onMalformedURLException(MalformedURLException e,
		// Object state) {
		// }
		//
		// @Override
		// public void onFacebookError(FacebookError e, Object state) {
		// }
		// });
	}

}