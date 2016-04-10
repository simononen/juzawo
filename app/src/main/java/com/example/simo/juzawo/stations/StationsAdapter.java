package com.example.simo.juzawo.stations;

import android.net.Uri;
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
        String base = "http://192.168.43.218:8000";
        imImage.setImageURI(Uri.withAppendedPath(Uri.parse(base), "/logos/shell.png"));
        tvbranchName.setText(station.getBranchName());
        tvLocation.setText(station.getLocation());

        // Return the completed view to render on screen
        return convertView;
    }
}
