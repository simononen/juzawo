package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

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
                .baseUrl("http://192.168.43.218:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StationsEndpoint stationsEndpoint = retrofit.create(StationsEndpoint.class);

        Call<List<Station>> call = stationsEndpoint.findNearestStations(3.3039399, 32.3130871, 10);

        call.enqueue(this);

        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        //}

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map) {
            Intent mapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
            /*StationDetails a = new StationDetails();
            double location_lat = Double.parseDouble(String.valueOf(a.getLat()));
            double location_lng = Double.parseDouble(String.valueOf(a.getLng()));

            Bundle bundle = new Bundle();
            bundle.putDouble("lat", location_lat);
            bundle.putDouble("lng", location_lng);
            mapActivityIntent.putExtras(bundle);*/
            startActivity(mapActivityIntent);
        }
        else if(id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
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
