package com.dotgears.berkshire.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dotgears.berkshire.R;

/**
 * Created by My PC on 10/12/2015.
 */
public class FeedBack extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarFeedBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Feed back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
