package com.example.simo.juzawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SignUp extends AppCompatActivity {

    private Button btn, loginBtn;
    private static final String POST_URI = "http://api.patrickoryono.co/auth/signup";
    private TextView mResult;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();// or getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                }
        );

        btn = (Button) findViewById(R.id.registration);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent regBtnIntent = new Intent(SignUp.this, Register.class);
                        startActivity(regBtnIntent);
                    }
                }
        );
    }
}
