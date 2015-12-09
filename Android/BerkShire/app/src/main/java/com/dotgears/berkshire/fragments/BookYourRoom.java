package com.dotgears.berkshire.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.dotgears.berkshire.R;
import com.dotgears.berkshire.activities.HotelActivity;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.joda.time.DateTime;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class BookYourRoom extends Fragment {
    @InjectView(R.id.spinner1)
    BetterSpinner spinner1;
    private EditText edtLocation;
    private Button btnAdd,btnSearch;
    private LinearLayout checkIn, checkOut;
    private TextView ckInday,ckInMonth,ckInYear,ckOutDay,ckOutMonth,ckOutYear;
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    String numberPersons="1";

    View rootView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.book_your_room, container, false);
//        EditText
        edtLocation = (EditText)rootView.findViewById(R.id.edtLocation);
//        Spinner
        spinner1 = (BetterSpinner)rootView.findViewById(R.id.spinner1);
        ButterKnife.inject(getActivity());
        String[] list = getResources().getStringArray(R.array.room);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_dropdown_item_1line, list);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                numberPersons = parent.getItemAtPosition(position).toString();
            }
        });

//        TextView
        ckInday = (TextView) rootView.findViewById(R.id.ckInDay);
        ckInMonth = (TextView) rootView.findViewById(R.id.ckInMonth);
        ckInYear = (TextView) rootView.findViewById(R.id.ckInYear);
        ckOutDay = (TextView) rootView.findViewById(R.id.ckOutDay);
        ckOutMonth = (TextView) rootView.findViewById(R.id.ckOutMonth);
        ckOutYear = (TextView) rootView.findViewById(R.id.ckOutYear);

//        LinearLayout
        checkIn = (LinearLayout) rootView.findViewById(R.id.ckInRow);
        checkOut = (LinearLayout) rootView.findViewById(R.id.ckOutRow);
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
                        ckInday.setText(String.valueOf(dayOfMonth));
                        ckInMonth.setText("TH"+String.valueOf(monthOfYear));
                        ckInYear.setText(String.valueOf(year));
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
                        ckOutDay.setText(String.valueOf(dayOfMonth));
                        ckOutMonth.setText("TH"+String.valueOf(monthOfYear));
                        ckOutYear.setText(String.valueOf(year));
                    }
                });
                return false;
            }
        });
//        Button
        btnAdd = (Button)rootView.findViewById(R.id.btnAdd);
        btnSearch = (Button)rootView.findViewById(R.id.btnSearch);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity().getApplicationContext(), HotelActivity.class);
                String idLocation = edtLocation.getText().toString();
                it.putExtra("idCategoryRoom", idLocation);
                it.putExtra("idLocation", numberPersons);
                startActivity(it);
//                Toast.makeText(getActivity(),numberPersons + edtLocation.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    


}
