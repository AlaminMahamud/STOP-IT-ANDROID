package com.ethereal.stopit.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.ethereal.stopit.EventDescriptionActivity;
import com.ethereal.stopit.JSONParser;
import com.ethereal1.stopit.R;
import com.ethereal.stopit.adapters.EventsListViewAdapter;
import com.ethereal.stopit.fragments.MostRecentEventFragment.PopulateListView;
import com.ethereal.stopit.objects.Event;
import com.ethereal.stopit.variables.ServerData;

public class MostVotedEventFragment extends Fragment {

	View view;
	
	ListView eventList;
	LinearLayout progressBar;
	EventsListViewAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater
				.inflate(R.layout.most_voted_or_recent_events, container, false);
		eventList = (ListView) view.findViewById(R.id.eventList);
		progressBar = (LinearLayout) view
				.findViewById(R.id.linlaHeaderProgress);
		// eventList.setVisibility(View.GONE);
		// progressBar.setVisibility(View.VISIBLE);
		new PopulateListView().execute();
		return view;
	}
	
class PopulateListView extends AsyncTask<Void, Void, Void> {
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			eventList.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("type", "most_voted"));
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONParser jsonParser = new JSONParser();
			json = jsonParser.makeHttpRequest(
					"http://ineedahelp.com/stopit/select.php", "GET",
					nameValuePairs);
			try {
				int success = json.getInt("success");
				if (success == 1) {
					JSONObject o = new JSONObject();
					jsonArray = json.getJSONArray("events");
					for (int i = 0; i < jsonArray.length(); i++) {
						o = jsonArray.getJSONObject(i);
						Event event = new Event();
						event.setId(o.getInt(ServerData.ID));
						event.setEventName(o.getString(ServerData.EVEVT_NAME));
						event.setName(o.getString(ServerData.CORRUPTOR_NAME));
						event.setWorkingPlace(o.getString(ServerData.WORKING_PLACE));
						event.setDistrict(o.getString(ServerData.DICTRICT));
						event.setUpzilla(o.getString(ServerData.UPZILLA));
						event.setThana(o.getString(ServerData.THANA));
						event.setDescription(o.getString(ServerData.DESCRIPTION));
						event.setProofLink(o.getString(ServerData.PROOF_LINK));
						event.setCreated_by(o.getString(ServerData.CREATED_BY));
						event.setVoteYes(o.getInt(ServerData.VOTE_YES));
						event.setVoteNo(o.getInt(ServerData.VOTE_NO));
						events.add(event);
					}
				} else {
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
			adapter =new EventsListViewAdapter(getActivity(), events);
			eventList.setAdapter(adapter);
			eventList.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			eventList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), events.get(position).getId()+"",Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(getActivity(),EventDescriptionActivity.class);
					intent.putExtra("event", events.get(position));
					getActivity().startActivity(intent);
				}
			});
		}

	}

}