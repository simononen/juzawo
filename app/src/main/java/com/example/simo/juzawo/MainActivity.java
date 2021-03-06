package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private List<StationDetails> stations;

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected TextView mLatitude;
    protected TextView mLongitude;
    protected LocationRequest mLocationRequest;
    private double l;
    private double lg;

    private Button retry;
    private ImageView noConnectioin;
    private TextView notificationText;
    private TextView connectionErrorText;

    private static Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        setContentView(R.layout.activity_main);
        //progressBar.setVisibility(View.VISIBLE);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        //listView = (ListView) findViewById(R.id.list);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading stations ....");

        builder();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

    }

    public void builder() {
        // create class object
        gps = new GPSTracker(MainActivity.this);

        //check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            //192.168.43.218:8080

            Retrofit retrofit = RetrofitBuilder.build();

            StationsEndpoint stationsEndpoint = retrofit.create(StationsEndpoint.class);

            Call<List<Station>> call = stationsEndpoint.findNearestStations(latitude, longitude, 10);

            call.enqueue(this);

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            gps.showSettingsAlert();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //setMenuBackground();
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
            mapActivityIntent.putExtras(bundle);
            startActivity(mapActivityIntent);
        } else if (id == R.id.refresh) {
            progressDialog.show();
            builder();
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

        progressDialog.dismiss();

        isInternetOn();

        final ArrayList<Station> stations = (ArrayList<Station>) response.body();
        ListView listView = (ListView) findViewById(R.id.list);
        final StationsAdapter custom = new StationsAdapter(this, R.layout.list_item, stations);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Station item = custom.getItem(position);
                Long result = item.getId();
                Intent i = new Intent(MainActivity.this, TabsDetailsActivity.class);
                Bundle b = new Bundle();
                b.putLong("TEXT", result);
                i.putExtras(b);
                i.putExtra("name", result);
                startActivity(i);
            }
        });
        listView.setAdapter(custom);

    }

    @Override
    public void onFailure(Call<List<Station>> call, Throwable t) {
        Log.e("http", "", t);
        isInternetOn();
        Toast.makeText(this, "Stations: ERROR ", Toast.LENGTH_LONG).show();

    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet
            retry = (Button) findViewById(R.id.button_retry);
            retry.setVisibility(View.INVISIBLE);
            noConnectioin = (ImageView) findViewById(R.id.image_no_connection);
            noConnectioin.setVisibility(View.INVISIBLE);
            notificationText = (TextView) findViewById(R.id.notification);
            notificationText.setVisibility(View.INVISIBLE);
            connectionErrorText = (TextView) findViewById(R.id.connection_failed);
            connectionErrorText.setVisibility(View.INVISIBLE);
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            retry = (Button) findViewById(R.id.button_retry);
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                    builder();
                }
            });
            noConnectioin = (ImageView) findViewById(R.id.image_no_connection);
            noConnectioin.setVisibility(View.VISIBLE);
            notificationText = (TextView) findViewById(R.id.notification);
            notificationText.setVisibility(View.VISIBLE);
            connectionErrorText = (TextView) findViewById(R.id.connection_failed);
            connectionErrorText.setVisibility(View.VISIBLE);
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
