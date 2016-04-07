package com.example.simo.juzawo.stations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simo.juzawo.MainActivity;
import com.example.simo.juzawo.R;

import java.util.ArrayList;

public class StationsAdapter extends ArrayAdapter<Station> {

    public StationsAdapter(MainActivity context, int layout, ArrayList<Station> stations) {
        super(context, layout, stations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the data item for this position
        Station station = getItem(position);


        TextView tvbranchName = (TextView) convertView.findViewById(R.id.branch_name);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.location);
        ImageView imImage = (ImageView) convertView.findViewById(R.id.image);

        // Populate the data into the template view using the data object
        tvLocation.setText(station.getLocation());
        tvbranchName.setText(station.getBranchName());
        imImage.setImageResource(R.drawable.marker);

        // Return the completed view to render on screen
        return convertView;
    }
}
