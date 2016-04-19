package com.example.simo.juzawo.prices;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.simo.juzawo.DetailsActivity;
import com.example.simo.juzawo.R;

import java.util.List;

public class PriceAdapter extends ArrayAdapter<Price> {

    public PriceAdapter(DetailsActivity context, int details_item, List<Price> prices) {
        super(context, details_item, prices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Price a = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details_items, parent, false);
        }
        // Lookup view for data population
        TextView fuelType = (TextView) convertView.findViewById(R.id.fuel_type);
        TextView fuelPrice = (TextView) convertView.findViewById(R.id.fuel_price);
        // Populate the data into the template view using the data object
        fuelType.setText(a.getPrice());
        fuelPrice.setText(a.getFuel_type().getName());

        // Return the completed view to render on screen
        return convertView;
    }


}
