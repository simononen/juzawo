package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simo.juzawo.gps.GPSTracker;
import com.example.simo.juzawo.stations.Station;
import com.example.simo.juzawo.stations.StationsAdapter;
import com.example.simo.juzawo.stations.StationsEndpoint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements LocationListener, Callback<List<Station>> {

    // GPSTracker class
    GPSTracker gps;
    private TextView mResult;
    private ProgressDialog mProgress;
    private ListView listView;
    private List<Station> stations;

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected TextView mLatitude;
    protected TextView mLongitude;
    protected LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //listView = (ListView) findViewById(R.id.list);

        // create class object
        gps = new GPSTracker(MainActivity.this);

         //check if GPS enabled
        //if (gps.canGetLocation()) {

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        //192.168.43.218:8080

        Retrofit retrofit = new Retrofit
                .Builder()//http://192.168.43.218:8080
                .baseUrl("http://192.168.43.218:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StationsEndpoint stationsEndpoint = retrofit.create(StationsEndpoint.class);

        Call<List<Station>> call = stationsEndpoint.findNearestStations(3.3039399, 32.3130871, 10);

        call.enqueue(this);

        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        //}
    }

    @Override
    public void onResponse(Response<List<Station>> response, Retrofit retrofit) {
        //Toast.makeText(this, "Stations: " + response.body().size(), Toast.LENGTH_LONG).show();

        final ArrayList<Station> stations = (ArrayList<Station>) response.body();

        listView = (ListView) findViewById(R.id.list);
        //listView.setAdapter(custom);
        final StationsAdapter custom = new StationsAdapter(this, R.layout.list_item, stations);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Station item = custom.getItem(position);
                long result = (id +1);
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle b = new Bundle();
                b.putLong("TEXT", result);
                i.putExtras(b);
                i.putExtra("name", result);
                startActivity(i);
                //Toast.makeText(getApplicationContext(), "Id: " + (id+1) , Toast.LENGTH_LONG).show();
            }
        });

        listView.setAdapter(custom);

        //Toast.makeText(getApplicationContext(), "Response", Toast.LENGTH_LONG).show();
        //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stations));
    }


    @Override
    public void onFailure(Throwable t) {
        Log.e("http", "", t);
        Toast.makeText(this, "Stations: ERROR ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {

        mLatitude.setText(String.valueOf(mLastLocation.getLatitude()));
        mLongitude.setText(String.valueOf(mLastLocation.getLongitude()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
