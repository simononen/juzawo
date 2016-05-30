package com.example.simo.juzawo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.simo.juzawo.http.RetrofitBuilder;
import com.example.simo.juzawo.stations.Station;
import com.example.simo.juzawo.stations.StationsEndpoint;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, Callback<List<Station>> {

    private static double l;
    private static double lg;
    private GoogleMap googleMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        
        Retrofit retrofit = RetrofitBuilder.build();

        StationsEndpoint stationsEndpoint = retrofit.create(StationsEndpoint.class);

        Call<List<Station>> call = stationsEndpoint.findNearestStations(2.7882929, 32.314581, 10);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        int cameraZoom = 8;

        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);

        List<Station> a = response.body();

        for (int i = 0; i < a.size(); i++){
            String tittle = a.get(i).getBrand_name();
            Double lt = Double.valueOf(a.get(i).getLat());
            Double lg = Double.valueOf(a.get(i).getLng());
            LatLng latLng = new LatLng(lt, lg);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(tittle);
            markerOptions.flat(true);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
            googleMap.addMarker(markerOptions);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, cameraZoom));
            Toast.makeText(getApplicationContext(), "ok" + a.size(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<List<Station>> call, Throwable t) {
        //setContentView(R.layout.connection_eror);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_terrain) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if(id == R.id.action_satelite) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if (id == R.id.action_hybrid) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if (id == R.id.action_normal) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        else if (id == R.id.action_none) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        }

        return super.onOptionsItemSelected(item);
    }
}
