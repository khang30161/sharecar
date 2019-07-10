package com.example.khang.sharecar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RentCar extends AppCompatActivity {
    String arr[] = {
            "Hà Nội",
            "Thành Phố Hồ CHí Minh",
            "Hải Phòng",
            "Cần Thơ", "Đà Nẵng", "Bà rịa - Vũng Tàu", "Long An", "Quãng Ninh", "Đồng Nai", "Bình Dương"};


    List<RentManagers> rentManagers = new ArrayList<>();
    DatabaseReference databaseReference;
    FirebaseDatabase firebase;
    private RecyclerView recyclerView;
    private RentCarAdapter rentCarAdapter;
    private TextView mLocation, mStartday, mEndday, mTvButton, mTvLoaixe;
    private Button mBtnStartday, mBtnEndday, mAddpro;
    private Spinner spinner;
    private ImageView logo;
    private RadioButton mRent, mNeedRent, mRentOto, mRentBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);

        databaseReference = FirebaseDatabase.getInstance().getReference("post" + "");
        logo = findViewById(R.id.logoo);
        mBtnEndday = findViewById(R.id.btn_refund);
        mBtnStartday = findViewById(R.id.btn_pickup);
        mEndday = findViewById(R.id.tv_refund);
        mStartday = findViewById(R.id.tv_pickup);
        mLocation = findViewById(R.id.tv_local);
        mRent = findViewById(R.id.renttttt);
        mNeedRent = findViewById(R.id.needrentt);
        mTvButton = findViewById(R.id.tv_button);
        spinner = findViewById(R.id.spinner);
        mAddpro = findViewById(R.id.add_product);
        mRentOto = findViewById(R.id.rb_oto);
        mRentBike = findViewById(R.id.rb_xemay);
        mTvLoaixe = findViewById(R.id.tv_loaixe);

        mAddpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentCar.this, RentCarFloat.class);
                startActivity(intent);
                finish();
            }
        });

        this.showDatePickerDialog();
        this.endDatePickerDialog();
        //Lấy đối tượng Spinner ra

        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spinner.setOnItemSelectedListener(new MyProcessEvent());
        mRent.setOnCheckedChangeListener(new Radio_check());
        mRentBike.setOnCheckedChangeListener(new Radio_check1());

        recyclerView = findViewById(R.id.rv_rentcar);
        recyclerView.setHasFixedSize(true);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentCar.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setupRecycleView();
        queryFilter();
    }

    private void endDatePickerDialog() {


        mEndday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append("");
                        strBuf.append(year);
                        strBuf.append("-");
                        strBuf.append(month + 1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);


                        mEndday.setText(strBuf.toString());
                        queryFilter();
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(RentCar.this, onDateSetListener, year, month, day);


                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }

    private void showDatePickerDialog() {
        mStartday.setOnClickListener(new View.OnClickListener() {
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

                        mStartday.setText(strBuf.toString());
                        queryFilter();
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(RentCar.this, onDateSetListener, year, month, day);


                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupRecycleView() {
        rentCarAdapter = new RentCarAdapter(rentManagers, getApplicationContext(), new RentCarAdapter.Action() {
            @Override
            public void onClickItem(RentManagers manager, int position) {
                Intent intent = new Intent(RentCar.this, SelectItem.class);
                intent.putExtra("rentManager", manager);
                startActivity(intent);
            }

            @Override
            public void onLongClickItem(RentManagers manager, int position) {

            }
        });
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RentCar.this);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rentCarAdapter);
    }


    private void queryFilter() {
        String local = spinner.getSelectedItem().toString();
        final String style = mTvButton.getText().toString();
        final String start = mStartday.getText().toString();
        final String style2 = mTvLoaixe.getText().toString();
        final String end = mEndday.getText().toString();
        databaseReference.orderByChild("location").equalTo(local)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        rentManagers.clear();
                        for (DataSnapshot rentSnapshot : dataSnapshot.getChildren()) {
                            RentManagers rentManager = rentSnapshot.getValue(RentManagers.class);
                            boolean isAdd = true;
                            if (rentManager.getUserIdBook() != null) {
                                continue;
                            }
//                            else if (rentManager.getStartdate().equals(start) && start == null) {
//                                continue;
//                            } else if (rentManager.getStartdate().equals(start) && rentManager.getEnddate().equals(end) && rentManager.getStyle().equals(phanquyen)) {
//                                rentManagers.add(rentManager);
//                            }
                            if (!start.equals("") && !start.equals(rentManager.getStartdate())) {
                                isAdd = false;
                            }
                            if (!end.equals("") && !end.equals(rentManager.getEnddate())) {
                                isAdd = false;
                            }
                            if (!style.equals("") && !style.equals(rentManager.getStyle())) {
                                isAdd = false;
                            }
                            if (!style2.equals("") && !style2.equals(rentManager.getCategogy())) {
                                isAdd = false;
                            }


                            if (isAdd) {
                                rentManagers.add(rentManager);
                            }
                        }

                        rentCarAdapter.notifyDataSetChanged();
                        if (rentCarAdapter.getItemCount() == 0) {
                            Toast.makeText(RentCar.this, "Giảm bớt điều kiện lọc hoặc đăng thông tin của bạn", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    class Radio_check implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (mRent.isChecked()) {
                mTvButton.setText("Cần Cho Thuê");
            } else if (mNeedRent.isChecked()) {
                mTvButton.setText("Cần Thuê");
            }
            queryFilter();

        }
    }

    class Radio_check1 implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (mRentBike.isChecked()) {
                mTvLoaixe.setText("Xe máy");
            } else if (mRentOto.isChecked()) {
                mTvLoaixe.setText("Xe Ô tô");
            }
            queryFilter();

        }
    }


    class MyProcessEvent implements
            AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source

            queryFilter();
        }

        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }
}

