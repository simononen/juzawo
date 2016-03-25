package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class Register extends AppCompatActivity implements RestTask.ProgressCallback,
        RestTask.ResponseCallback {

    private static final String POST_URI = "http://api.patrickoryono.co/auth/signup";
    private TextView mResult;
    private ProgressDialog mProgress;
    private Button btnSubmit;

    private static String firstName;
    private static String lastName;
    private static String email;
    private static String phone;

    private EditText fName;
    private EditText lName;
    private EditText eMail;
    private EditText pNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();// or getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        btnSubmit = (Button) findViewById(R.id.submit);
        try {
            btnSubmit.setOnClickListener(
                    new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             display();
                         }
                     }
            );
        }catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    public void display() {

        fName = (EditText) findViewById(R.id.firstnametext);
        lName = (EditText) findViewById(R.id.lastnametext);
        eMail = (EditText) findViewById(R.id.emailtext);
        pNumber = (EditText) findViewById(R.id.phonenumbervalue);

        firstName = fName.getText().toString();
        lastName = lName.getText().toString();
        email = eMail.getText().toString();
        //phone = Integer.parseInt( pNumber.getText().toString() );
        phone = pNumber.getText().toString();

//        StringBuilder validationErrorMessage = new StringBuilder(R.string.validationerrormessage);
/*        if(firstName.length() == 0 || firstName.length() <= 3) {
            validationErrorMessage.append(getString(R.string.firstnameerror));

            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

        }else if(lastName.length() == 0) {
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

        }else if(email.length() == 0){
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

        }else if(phone.length() == 0 && phone.length() > 10){
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

        }*/

      /*  if (firstName.length() <3 || lastName.length() <3 || email.length() == 0 || phone.length() == 0 ){
            Toast.makeText(getApplicationContext(), "Please fill in the fields.", Toast.LENGTH_LONG).show();
        }*/

        //Create the request
        try{
            //Registering a user
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("firstname", firstName));
            parameters.add(new BasicNameValuePair("lastname", lastName));
            parameters.add(new BasicNameValuePair("email", email));
            parameters.add(new BasicNameValuePair("phone", phone));
            RestTask postTask = RestUtil.obtainFormPostTask(POST_URI, parameters);
            postTask.setResponseCallback(this);
            postTask.setProgressCallback(this);

            postTask.execute();

            //Display progress to the user
            mProgress = ProgressDialog.show(Register.this, "Registering",
                    "Please wait...", true);
        } catch (Exception e) {
            mResult.setText(e.getMessage());
        }


    }

    @Override
    public void onProgressUpdate(int progress) {
        if (progress >= 0) {
            if (mProgress != null) {
                mProgress.dismiss();
                mProgress = null;
            }
        //Update user of progress
            mResult.setText( String.format(
                    "Download Progress: %d%%", progress));
        }
    }

    /*@Override
    public void onRequestSuccess(String response) {
        //Clear progress indicator
        if(mProgress != null) {
            mProgress.dismiss();
        }

        setContentView(R.layout.success);

        //Process the response data (here we just display it)
        //mResult.setText(response);
    }*/

    @Override
    public void onRequestSuccess(String response) {

    }

    @Override
    public void onRequestError(Exception error) {
        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

    }

}