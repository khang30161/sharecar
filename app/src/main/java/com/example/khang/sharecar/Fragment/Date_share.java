package com.example.khang.sharecar.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

import static android.content.Context.MODE_PRIVATE;


public class Date_share extends Fragment {
    private Button mStartdate,mStarttime, mEndtime;
    private TextView mTvStartdate, mTvStarttime, mTvEndtime;
    String prefname = "my_data";
    private OnFragmentInteractionListener mListener;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_date_share, container, false);
        mStartdate=view.findViewById(R.id.btn_starttime);
        mTvStartdate=view.findViewById(R.id.tv_startdate);
        mStarttime=view.findViewById(R.id.btn_starttimee);
        mTvStarttime=view.findViewById(R.id.tv_starttime);
        mEndtime=view.findViewById(R.id.btn_endtime);
        mTvEndtime=view.findViewById(R.id.tv_endtime);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        pushdata();
        transaction.commit();
        showDatePickerDialog();
        showTimePickerDialog();
        showEndTimePickerDialog();
        return view;
    }

    private void pushdata() {
        String prefname = "my_data";
        SharedPreferences pre = getActivity().getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String startdate = mTvStartdate.getText().toString();
        String starttime = mTvStarttime.getText().toString();
        String endtime = mTvEndtime.getText().toString();

        editor.putString("startdate", startdate);
        editor.putString("starttime", starttime);
        editor.putString("endtime", endtime);
        editor.apply();
    }

    public static Date_share newInstance(int page, String title) {
        Date_share date_share = new Date_share();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        date_share.setArguments(args);
        return date_share;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Date_share.OnFragmentInteractionListener) {
            mListener = (Date_share.OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of Date_Fragment");
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void showEndTimePickerDialog() {
        mEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append(" ");
                        strBuf.append(hourOfDay);
                        strBuf.append("");
                        strBuf.append(minute+"phut");

                        mTvEndtime.setText(strBuf.toString());
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

    private void showTimePickerDialog() {
        mStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        StringBuffer strBufh = new StringBuffer();

                        strBufh.append(hourOfDay);
                        StringBuffer m=new  StringBuffer();
                        m.append("h");
                        m.append(minute+"phut");

                        //mTvStarttime.setText(strBuf.toString());
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
