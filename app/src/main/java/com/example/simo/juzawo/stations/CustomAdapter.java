/*
package com.example.simo.juzawo.stations;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.simo.juzawo.R;

import java.util.List;

public class CustomAdapter<Stations> extends ArrayAdapter<Station> {

    private static class ViewHolder {
        TextView tvbranchName;
        TextView tvLocation;
    }

    public CustomAdapter(Context context, int resource, List<Station> stations) {
        super(context, resource, stations);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent ) {
        // Get the data item for this position
        Station station = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder.tvbranchName = (TextView) convertView.findViewById(R.id.branch_name);
            viewHolder.tvLocation = (TextView) convertView.findViewById(R.id.location);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.tvbranchName.setText(station.branchName);
        viewHolder.tvLocation.setText(station.location);
        // Return the completed view to render on screen
        return convertView;
    }
}
*/
