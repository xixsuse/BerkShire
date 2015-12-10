package com.dotgears.berkshire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.dotgears.berkshire.R;

/**
 * Created by My PC on 10/12/2015.
 */
public class InviteFriend extends AppCompatActivity{

    Button btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_friend);
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarInviteFriend);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Invite Friends");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        btnShare = (Button)findViewById(R.id.btnInvite);
        btnShare.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Download BerkShire App for free booking experience \n http://www.bbg.vtv.vn";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BerkShire");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
