package com.dotgears.reproom.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.dotgears.reproom.R;

import org.joda.time.DateTime;

/**
 * Created by My PC on 18/11/2015.
 */
public class BookingYourRoom extends Fragment {
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    private TextView checkIn, checkOut;
    View rootView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.booking_your_room, container, false);
        checkIn = (TextView) rootView.findViewById(R.id.checkIn);
        checkOut = (TextView) rootView.findViewById(R.id.checkOut);

        checkIn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DateTime now = DateTime.now();
                CalendarDatePickerDialogFragment cdpCheckIn = CalendarDatePickerDialogFragment
                        .newInstance(null, now.getYear(), now.getMonthOfYear() - 1,
                                now.getDayOfMonth());
                cdpCheckIn.show(fm, FRAG_TAG_DATE_PICKER);
                cdpCheckIn.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment calendarDatePickerDialogFragment, int year, int monthOfYear, int dayOfMonth) {
                        checkIn.setText("Year: " + year + "\nMonth: " + monthOfYear + "\nDay: " + dayOfMonth);
                    }
                });
                return false;
            }
        });
        checkOut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DateTime now = DateTime.now();
                CalendarDatePickerDialogFragment cdpCheckIn = CalendarDatePickerDialogFragment
                        .newInstance(null, now.getYear(), now.getMonthOfYear() - 1,
                                now.getDayOfMonth());
                cdpCheckIn.show(fm, FRAG_TAG_DATE_PICKER);
                cdpCheckIn.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment calendarDatePickerDialogFragment, int year, int monthOfYear, int dayOfMonth) {
                        checkOut.setText("Year: " + year + "\nMonth: " + monthOfYear + "\nDay: " + dayOfMonth);
                    }
                });
                return false;
            }
        });

        return rootView;
    }



}
