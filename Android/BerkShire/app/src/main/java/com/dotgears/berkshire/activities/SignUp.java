package com.dotgears.berkshire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dotgears.berkshire.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import butterknife.ButterKnife;

/**
 * Created by My PC on 08/12/2015.
 */
public class SignUp extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView edtAHAA;
    BetterSpinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.sign_up);
// Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarSignUp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        loginButton = (LoginButton) findViewById(R.id.sign_up_button);
        loginButton.setReadPermissions("user_friends");
        // If using in a fragment
//        loginButton.setFragment(SignIn.this);
        // Other app specific specialization
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                loginResult.getAccessToken();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        edtAHAA = (TextView)findViewById(R.id.edtAHAA);
        edtAHAA.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent itSignIn = new Intent(getApplicationContext(), SignIn.class);
                startActivity(itSignIn);
                return false;
            }
        });
        //        Spinner
        spinner1 = (BetterSpinner)findViewById(R.id.spinner1);
        ButterKnife.inject(this);
        String[] list = getResources().getStringArray(R.array.phone);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                numberPersons = parent.getItemAtPosition(position).toString();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}