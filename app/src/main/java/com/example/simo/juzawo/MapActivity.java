package com.example.simo.juzawo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.simo.juzawo.http.RetrofitBuilder;
import com.example.simo.juzawo.stations.Station;
import com.example.simo.juzawo.stations.StationsEndpoint;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
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

        Call<List<Station>> call = stationsEndpoint.findNearestStations(3.3039399, 32.3130871, 10);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
        //Station st = (Station) response.body();
        ArrayList<Station> a = (ArrayList<Station>) response.body();
        for (int i = 0; i < a.size(); i++){
            Toast.makeText(getApplicationContext(), "ok"+a.get(i), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<List<Station>> call, Throwable t) {

    }
}
