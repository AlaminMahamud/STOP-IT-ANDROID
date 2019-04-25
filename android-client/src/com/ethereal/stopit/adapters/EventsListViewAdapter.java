package com.ethereal.stopit.adapters;

import java.util.ArrayList;

import com.ethereal1.stopit.R;
import com.ethereal.stopit.objects.Event;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class EventsListViewAdapter extends BaseAdapter {
	
	private Context context;
    private ArrayList<Event> events;
     
    public EventsListViewAdapter(Context context, ArrayList<Event> navDrawerItems){
        this.context = context;
        this.events = navDrawerItems;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {       
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.single_item_event, null);
        }
          
        TextView eventName =(TextView) convertView.findViewById(R.id.eventName);
        TextView corruptorName =(TextView) convertView.findViewById(R.id.corruptor_name);
        TextView workingPlace =(TextView) convertView.findViewById(R.id.workingPlace);
        TextView voteYes =(TextView) convertView.findViewById(R.id.voteYes);
        TextView voteNo =(TextView) convertView.findViewById(R.id.voteNo);
                 
        eventName.setText(events.get(position).getEventName());
        corruptorName.setText(events.get(position).getName());
        workingPlace.setText(events.get(position).getWorkingPlace());
        voteYes.setText(events.get(position).getVoteYes()+"");
        voteNo.setText(events.get(position).getVoteNo()+"");
         
        return convertView;
    }
}