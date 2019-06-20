package com.example.khang.sharecar.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.khang.sharecar.R;
import com.example.khang.sharecar.RentCar;

import java.util.Calendar;
import java.util.Objects;


public class Date_share extends Fragment {
    private Button mStartdate,mStarttime, mEndtime;
    private TextView mTvStartdate, mTvStarttime;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_date_share, container, false);
        mStartdate=view.findViewById(R.id.btn_starttime);
        mTvStartdate=view.findViewById(R.id.tv_startdate);
        mStarttime=view.findViewById(R.id.btn_starttimee);
        mTvStarttime=view.findViewById(R.id.tv_starttime);
        showDatePickerDialog();
        showTimePickerDialog();
        return view;
    }

    private void showTimePickerDialog() {
        mStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append(" ");
                        strBuf.append(hourOfDay);
                        strBuf.append("h");
                        strBuf.append(minute+"phut");

                        mTvStarttime.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar calendar = Calendar.getInstance();
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Create the new DatePickerDialog instance.
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), onTimeSetListener, hour, minute, true);



                // Popup the dialog.
                timePickerDialog.show();
            }
        });
    }


    private void showDatePickerDialog() {
        mStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append(" ");
                        strBuf.append(year);
                        strBuf.append("-");
                        strBuf.append(month+1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);

                        mTvStartdate.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);



                // Popup the dialog.
                datePickerDialog.show();
            }
        });

}}
