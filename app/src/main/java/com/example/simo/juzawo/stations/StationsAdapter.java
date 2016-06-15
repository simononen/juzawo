package com.example.simo.juzawo.stations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simo.juzawo.MainActivity;
import com.example.simo.juzawo.R;
import com.squareup.picasso.Picasso;

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
        //TextView tvdiesel = (TextView) convertView.findViewById(R.id.diesel_price);
        //TextView tvpetrol = (TextView) convertView.findViewById(R.id.petrol_price);

        // Populate the data into the template view using the data object
        //String base = "http://192.168.43.218:8000";
        //imImage.setImageURI(Uri.withAppendedPath(Uri.parse(base), "/logos/shell.png"));
        tvbranchName.setText(station.getBrand_name());
        tvLocation.setText(" " + station.getLocation());
        String url = "http://api.patrickoryono.co/"+station.getLogo();
        Picasso.with(getContext()).load(url).into(imImage);


        // Return the completed view to render on screen
        return convertView;
    }
}
