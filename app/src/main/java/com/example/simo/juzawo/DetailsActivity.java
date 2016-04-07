package com.example.simo.juzawo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simo.juzawo.prices.Prices;
import com.example.simo.juzawo.stationdetails.StationDetails;
import com.example.simo.juzawo.stations.StationsEndpoint;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class DetailsActivity extends AppCompatActivity implements Callback<StationDetails> {

    private long at;
    private TextView txtPetrol;
    private TextView txtDiesel;
    private TextView txtKerosene;
    private TextView txtGas;
    private TextView txtDetails;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt1 = (TextView) findViewById(R.id.text);



        Bundle b = this.getIntent().getExtras();
        at = b.getLong("TEXT");



        Retrofit retrofit = new Retrofit
                .Builder()//http://192.168.43.218:8080
                .baseUrl("http://192.168.43.218:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StationsEndpoint stationsEndpoint = retrofit.create(StationsEndpoint.class);

        Call<StationDetails> call = stationsEndpoint.findStation(at);

        call.enqueue(this);

    }


    @Override
    public void onResponse(Response<StationDetails> response, Retrofit retrofit) {
        Toast.makeText(getApplicationContext(), "working", Toast.LENGTH_SHORT).show();
        ListView list;
        //list = (ListView) findViewById(R.id.list1);

        StationDetails station = response.body();
        List<Prices> s = station.getPrices();
        for(int i = 1; i < s.size(); i++) {
            txtPetrol.setText("" + s.get(i).getPrice());

        }

        String[] myStringArray={"A","B","C"};
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                myStringArray);
        ListView myList=
                (ListView) findViewById(R.id.list1);
        myList.setAdapter(myAdapter);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


    }
}
