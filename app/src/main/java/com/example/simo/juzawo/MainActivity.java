package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RestTask.ProgressCallback, RestTask.ResponseCallback {

    // GPSTracker class
    GPSTracker gps;
    private TextView mResult;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        ScrollView scrollView = new ScrollView(this);
        mResult = new TextView(this);
        scrollView.addView(mResult, new ViewGroup.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        setContentView(scrollView);

        // create class object
        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            //192.168.43.218:8080
            final String SEARCH_URI = "http://192.168.43.233:8080/stations?lat=" + latitude + "&long=" + longitude + "&limit=" + 10;

            //Create the request
            try {
                //Simple GET
                RestTask getTask = RestUtil.obtainGetTask(SEARCH_URI);
                getTask.setResponseCallback(this);
                getTask.setProgressCallback(this);

                getTask.execute();

                //Display progress to the user
                mProgress = ProgressDialog.show(this, "Searching for stations",
                        "Waiting For Results...", true);
            } catch (Exception e) {
                mResult.setText(e.getMessage());
            }

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
        else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

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
        if (id == R.id.action_update) {
            Intent i = new Intent(this, SignUp.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressUpdate(int progress) {

        if (progress >= 0) {
            if (mProgress != null) {
                mProgress.dismiss();
                mProgress = null;
            }
            //Update user of progress
            mResult.setText(String.format("Download Progress: %d%%", progress));
        }

    }

    @Override
    public void onRequestSuccess(String response) {
        mProgress.dismiss();
        mResult.setText(response);

    }

    @Override
    public void onRequestError(Exception error) {
        //Clear progress indicator
        if(mProgress != null) {
            mProgress.dismiss();
        }
        //Process the response data (here we just display it)
        mResult.setText("An Error Occurred: "+error.getMessage());

    }
}
