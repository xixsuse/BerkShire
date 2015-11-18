package com.dotgears.reproom.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dotgears.reproom.R;

/**
 * Created by My PC on 18/11/2015.
 */
public class BookingYourRoom extends Fragment {
    View rootView=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.booking_your_room,container,false);
        return rootView;
    }
}
