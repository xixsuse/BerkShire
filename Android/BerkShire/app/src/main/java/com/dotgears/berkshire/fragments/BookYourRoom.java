package com.dotgears.berkshire.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.weiwangcn.betterspinner.library.BetterSpinner;
import com.dotgears.berkshire.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.joda.time.DateTime;


public class BookYourRoom extends Fragment {
    @InjectView(R.id.spinner1)
    BetterSpinner spinner1;
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    private TextView checkIn, checkOut;
    View rootView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.book_your_room, container, false);
        spinner1 = (BetterSpinner)rootView.findViewById(R.id.spinner1);
        ButterKnife.inject(getActivity());

        String[] list = getResources().getStringArray(R.array.month);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_dropdown_item_1line, list);

        spinner1.setAdapter(adapter);


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
