package com.example.simo.juzawo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.simo.juzawo.models.StationsModel;

import java.util.ArrayList;

public class StationsAdapter extends ArrayAdapter<StationsModel> {
    // View lookup cache

    private static class ViewHolder {
        TextView name;
        TextView home;
    }

    public StationsAdapter(Context context, ArrayList<StationsModel> stations) {
        super(context, R.layout.list_item, stations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        StationsModel station = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.branch_name);
            viewHolder.home = (TextView) convertView.findViewById(R.id.location);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object

        viewHolder.name.setText(station.branchName);
        viewHolder.home.setText(station.location);
        // Return the completed view to render on screen

        return convertView;
    }
}
