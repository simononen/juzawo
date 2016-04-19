package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simo.juzawo.gps.GPSTracker;
import com.example.simo.juzawo.http.RetrofitBuilder;
import com.example.simo.juzawo.stationdetails.StationDetails;
import com.example.simo.juzawo.stations.Station;
import com.example.simo.juzawo.stations.StationsAdapter;
import com.example.simo.juzawo.stations.StationsEndpoint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import retrofit2.GsonConverterFactory;

//import com.example.simo.juzawo.stations.Station;

public class MainActivity extends AppCompatActivity implements LocationListener, Callback<List<Station>> {

    // GPSTracker class
    GPSTracker gps;
    private TextView mResult;
    private ProgressDialog mProgress;
    private List<StationDetails> stations;

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected TextView mLatitude;
    protected TextView mLongitude;
    protected LocationRequest mLocationRequest;
    private double l;
    private double lg;

    private static Bundle bundle = new Bundle();

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

        Retrofit retrofit = RetrofitBuilder.build();

        StationsEndpoint stationsEndpoint = retrofit.create(StationsEndpoint.class);

        Call<List<Station>> call = stationsEndpoint.findNearestStations(3.3039399, 32.3130871, 40);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //setMenuBackground();
        return true;
    }

    private void setMenuBackground() {
        getLayoutInflater().setFactory(new LayoutInflater.Factory() {
            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                if (name.equalsIgnoreCase("com.android.internal.view")) {
                    try {
                        LayoutInflater f = getLayoutInflater();
                        final View view = f.createView(name, null, attrs);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                view.setBackgroundResource(R.color.colorHighlight);
                            }
                        });
                        return view;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e);
                    }
                }
                return null;
            }
        });
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
            /*Station a = new Station();
            double b = a.setLat(2.234567);
            double c = a.setLng(32.234567);
            double location_lat = a.getLat();
            double location_lng = a.getLng();*/

            mapActivityIntent.putExtras(bundle);
            startActivity(mapActivityIntent);
        }
        else if(id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
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


    @Override
    public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {

        final ArrayList<Station> stations = (ArrayList<Station>) response.body();
        ListView listView = (ListView) findViewById(R.id.list);
        final StationsAdapter custom = new StationsAdapter(this, R.layout.list_item, stations);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Station item = custom.getItem(position);
                Long result = item.getId();
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

        List<Station> stns = response.body();
/*        for (int i = 0; i < stns.size(); i++){
            l = stns.get(i).getLat();
            lg = stns.get(i).getLng();
            Toast.makeText(getApplicationContext(), "latitude "+stns.get(i).getLat()+" longitude "+stns.get(i).getLng(), Toast.LENGTH_SHORT).show();
            bundle.putDouble("lat", l);
            bundle.putDouble("lng", lg);
        }*/

        //Toast.makeText(getApplicationContext(), "Response", Toast.LENGTH_LONG).show();
        //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stations));

    }

    @Override
    public void onFailure(Call<List<Station>> call, Throwable t) {
        Log.e("http", "", t);
        Toast.makeText(this, "Stations: ERROR ", Toast.LENGTH_LONG).show();

    }
}
