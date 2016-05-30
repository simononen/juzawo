package com.example.simo.juzawo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simo.juzawo.http.RetrofitBuilder;
import com.example.simo.juzawo.stations.Station;
import com.example.simo.juzawo.stations.StationsEndpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import retrofit2.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity implements Callback<Station> {

    private long at;
    private String stringword;
    private TextView txtPetrol;
    private TextView txtDiesel;
    private TextView txtKerosene;
    private TextView txtGas;
    private TextView txtDetails;
    private TextView txt1;
    public ImageView iconImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt1 = (TextView) findViewById(R.id.text);



        Bundle b = this.getIntent().getExtras();
        at = b.getLong("TEXT");
        stringword = b.getString("name");



        Retrofit retrofit = RetrofitBuilder.build();

        StationsEndpoint stationsEndpoint =  retrofit.create(StationsEndpoint.class);
        Call<Station> call = stationsEndpoint.findStation(at);

        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<Station> call, Response<Station> response) {
        Station stat = response.body();

        ActionBar actionBar = getSupportActionBar();

        //String url = "http://192.168.43.70:8080/"+stat.getLogo();

        if (actionBar != null) {
            actionBar.setTitle(stat.getBrand_name());
            actionBar.setSubtitle(stat.getLocation());
            //actionBar.setIcon(Integer.parseInt(url));
            
        }

        ListView myList= (ListView) findViewById(R.id.list1);

        //PriceAdapter adapter = new PriceAdapter(this, R.layout.details_items, stat.getPrice());

        //myList.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<Station> call, Throwable t) {
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

    }
}
