package com.example.khang.sharecar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShareCarCustomer extends AppCompatActivity {
    List<ShareManager> shareManager = new ArrayList<>();
    DatabaseReference databaseReference;
    FirebaseDatabase firebase;
    private RecyclerView recyclerView;
    private ShareCarAdapter shareCarAdapter;
    private JSONArray dataLocation;
    private JSONArray districtListSelect;
    private JSONArray wardListSelect;
    private Spinner mSpinner, mSpinnerDistrict, mSpinnerWard, mSpinner1, mSpinnerDistrict1, mSpinnerWard1;
    private Button mStartdate;
    private TextView mTvStartdate, mTvdisplay;
    private RadioButton mMin, mMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_car_customer);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Share").child("post" + "");
        recyclerView = findViewById(R.id.rv_customer);
        recyclerView.setHasFixedSize(true);
        readJsonLocation();

        mSpinner = findViewById(R.id.spinner_share);
        mSpinnerDistrict = findViewById(R.id.spinner_district);
        mSpinnerWard = findViewById(R.id.spinner_war);
        mSpinner1 = findViewById(R.id.spinner_share1);
        mSpinnerDistrict1 = findViewById(R.id.spinner_district1);
        mSpinnerWard1 = findViewById(R.id.spinner_war1);
        mStartdate = findViewById(R.id.btn_startdate);
        mTvStartdate = findViewById(R.id.tv_startdate);
        mMin = findViewById(R.id.rb_min1);
        mMax=findViewById(R.id.rb_max);
        mTvdisplay=findViewById(R.id.tv_display);
        showDatePickerDialog();


        final ArrayList<String> arrCityList = new ArrayList<>();

        try {
            for (int i = 0; i < dataLocation.length(); i++) {
                JSONObject city = dataLocation.getJSONObject(i);
                arrCityList.add(city.getString("city"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arrCityList);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        mSpinner.setAdapter(adapter);
        mSpinner1.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinner.setOnItemSelectedListener(new MyProcessEvent());

        mSpinner1.setOnItemSelectedListener(new MyProcessEvent1());
        mMin.setOnCheckedChangeListener(new RadioCheck());
        setupRecycleview();
//        queryFilter();

    }
    class RadioCheck  implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(mMin.isChecked()){
                mTvdisplay.setText("2-3 chỗ");
            }
            if(mMax.isChecked()){
                mTvdisplay.setText("4-5 chỗ");
            }
            queryFilter();
        }
    }

    private void showSpinnerDistrict() {
        mSpinnerDistrict.setVisibility(View.VISIBLE);
        final ArrayList<String> arrDistrictList = new ArrayList<>();

        try {
            for (int i = 0; i < districtListSelect.length(); i++) {
                JSONObject city = districtListSelect.getJSONObject(i);
                arrDistrictList.add(city.getString("district_name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arrDistrictList);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        mSpinnerDistrict.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int index,
                                       long arg3) {
                try {
                    JSONObject district = districtListSelect.getJSONObject(index);
                    wardListSelect = district.getJSONArray("ward");
                    Log.e("TEst", wardListSelect.toString());
                    showSpinnerWard();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                if (local == "TP.Hồ Chí Minh") {
//
//                    loadFragment(new hcm_map());
//
//                }
                //queryFilter();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void showSpinnerWard() {
        mSpinnerWard.setVisibility(View.VISIBLE);
        final ArrayList<String> arrWardList = new ArrayList<>();

        try {
            for (int i = 0; i < wardListSelect.length(); i++) {
                //  JSONArray city = wardListSelect.getJSONArray(i);

                arrWardList.add(wardListSelect.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arrWardList);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        mSpinnerWard.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinnerWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int index,
                                       long arg3) {
                queryFilter();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void showSpinnerDistrict1() {
        mSpinnerDistrict1.setVisibility(View.VISIBLE);
        final ArrayList<String> arrDistrictList = new ArrayList<>();

        try {
            for (int i = 0; i < districtListSelect.length(); i++) {
                JSONObject city = districtListSelect.getJSONObject(i);
                arrDistrictList.add(city.getString("district_name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arrDistrictList);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        mSpinnerDistrict1.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinnerDistrict1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int index,
                                       long arg3) {
                try {
                    JSONObject district = districtListSelect.getJSONObject(index);
                    wardListSelect = district.getJSONArray("ward");
                    Log.e("TEst", wardListSelect.toString());
                    showSpinnerWard1();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                if (local == "TP.Hồ Chí Minh") {
//
//                    loadFragment(new hcm_map());
//
//                }
                queryFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void showSpinnerWard1() {
        mSpinnerWard1.setVisibility(View.VISIBLE);
        final ArrayList<String> arrWardList = new ArrayList<>();

        try {
            for (int i = 0; i < wardListSelect.length(); i++) {
                //  JSONArray city = wardListSelect.getJSONArray(i);

                arrWardList.add(wardListSelect.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arrWardList);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        mSpinnerWard1.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinnerWard1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int index,
                                       long arg3) {
                queryFilter();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void setupRecycleview() {
        shareCarAdapter = new ShareCarAdapter(shareManager, getApplicationContext(), new ShareCarAdapter.Action() {

            @Override
            public void onClickItem(ShareManager shareManager, int position) {
                Intent intent = new Intent(ShareCarCustomer.this, SelectItemShareCustomer.class);
                intent.putExtra("shareManager", shareManager);
                startActivity(intent);

            }

            @Override
            public void onLongClickItem(ShareManager shareManager, int position) {

            }
        });
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(ShareCarCustomer.this);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(shareCarAdapter);
    }

    private void queryFilter() {
        String ngaybđ = mTvStartdate.getText().toString();
        final String phanquyen = mTvdisplay.getText().toString();
        final String citystart = mSpinner.getSelectedItem().toString();
        final String cityend = mSpinner1.getSelectedItem().toString();
        final String districtstart = mSpinnerDistrict.getSelectedItem().toString();
        final String districtend = mSpinnerDistrict1.getSelectedItem().toString();
        final String wardstart = mSpinnerWard.getSelectedItem().toString();
        final String wardend = mSpinnerWard1.getSelectedItem().toString();
        databaseReference.orderByChild("startday").equalTo(ngaybđ)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        shareManager.clear();
                        for (DataSnapshot rentSnapshot : dataSnapshot.getChildren()) {
                            ShareManager shareManagers = rentSnapshot.getValue(ShareManager.class);
                            boolean isadd = true;
                            int seat = Integer.parseInt(shareManagers.getSeat());
                            if (shareManagers.getIsbooking1() != null && shareManagers.getIsBooking2() != null && shareManagers.getIsBooking3() != null && seat == 4) {
                                continue;
                            } else if (shareManagers.getIsbooking1() != null && shareManagers.getIsBooking2() != null && shareManagers.getIsBooking3() != null && shareManagers.getIsBooking4() != null && shareManagers.getIsBooking5() != null && seat == 6) {
                                continue;

                            }
                            if (!citystart.equals("") && !citystart.equals(shareManagers.getLocalcity())) {
                                isadd = false;
                            }

                            if (!districtstart.equals("") && !districtstart.equals(shareManagers.getLocalquan())) {
                                isadd = false;
                            }
                            if (!wardstart.equals("") && !wardstart.equals(shareManagers.getLocalphuong())) {
                                isadd = false;
                            }
                            if (!cityend.equals("") && !cityend.equals(shareManagers.getLocalcity1())) {
                                isadd = false;
                            }
                            if (!districtend.equals("") && !districtend.equals(shareManagers.getLocalquan1())) {
                                isadd = false;
                            }
                            if (!wardend.equals("") && !wardend.equals(shareManagers.getLocalphuong1())) {
                                isadd = false;
                            }
                            if (!phanquyen.equals("") && !phanquyen.equals(shareManagers.getSovitri())) {
                                isadd = false;
                            }
                            if (isadd) {
                                shareManager.add(shareManagers);
                            }
                        }
                        shareCarAdapter.notifyDataSetChanged();
                        if (shareCarAdapter.getItemCount() == 0) {
                            Toast.makeText(ShareCarCustomer.this, "Giảm bớt điều kiện lọc hoặc đăng thông tin của bạn", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void readJsonLocation() {
        String json = null;
        try {
            InputStream is = getAssets().open("tphcm.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Log.e("TEst", json);
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("data");
            dataLocation = data;
//            Log.e("TEst", data.toString());
//
//            for (int i = 0; i < data.length(); i++) {
//                JSONObject district = data.getJSONObject(i);
//                Log.d("TEst", district.getString("district_name"));
//                Log.d("TEst", district.getJSONArray("ward").toString());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                        strBuf.append(month + 1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);

                        mTvStartdate.setText(strBuf.toString());
                        queryFilter();
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(ShareCarCustomer.this, onDateSetListener, year, month, day);


                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }

    class MyProcessEvent implements
            AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int index,
                                   long arg3) {

            try {
                JSONObject city = dataLocation.getJSONObject(index);
                districtListSelect = city.getJSONArray("district");

                Log.e("TEst", districtListSelect.toString());

                showSpinnerDistrict();


            } catch (JSONException e) {
                e.printStackTrace();
            }

            //  queryFilter();
        }

        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    class MyProcessEvent1 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int index,
                                   long arg3) {
            try {
                JSONObject city = dataLocation.getJSONObject(index);
                districtListSelect = city.getJSONArray("district");

                Log.e("TEst", districtListSelect.toString());

                showSpinnerDistrict1();


            } catch (JSONException e) {
                e.printStackTrace();
            }
            //queryFilter();

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


}

