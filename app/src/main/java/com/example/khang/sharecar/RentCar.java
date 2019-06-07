package com.example.khang.sharecar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;


public class RentCar extends AppCompatActivity {
    String arr[]={
            "Hà Nội",
            "Thành Phố Hồ CHí Minh",
            "Hải Phòng",
            "Cần Thơ","Đà Nẵng", "Bà rịa - Vũng Tàu", "Long An", "Quãng Ninh", "Đồng Nai", "Bình Dương"};


    List <RentManagers> rentManagers=new ArrayList<>();
    private RecyclerView recyclerView;
    private RentCarAdapter rentCarAdapter;
    private TextView mLocation, mStartday, mEndday;
    private Button mBtnStartday, mBtnEndday;
    private Spinner spinner;
    DatabaseReference databaseReference;
    FirebaseDatabase firebase;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        databaseReference=FirebaseDatabase.getInstance().getReference("post" + "");
        logo=findViewById(R.id.logoo);
        mBtnEndday=findViewById(R.id.btn_refund);
        mBtnStartday=findViewById(R.id.btn_pickup);
        mEndday=findViewById(R.id.tv_refund);
        mStartday=findViewById(R.id.tv_pickup);
        mLocation=findViewById(R.id.tv_local);
        spinner=findViewById(R.id.spinner);
        this.showDatePickerDialog();
        this.endDatePickerDialog();
        //Lấy đối tượng Spinner ra

        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spinner.setOnItemSelectedListener(new MyProcessEvent());

        recyclerView=findViewById(R.id.rv_rentcar);
        recyclerView.setHasFixedSize(true);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(RentCar.this, RentCarFloat.class);
                startActivity(intent);
                finish();
            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RentCar.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        }

    class MyProcessEvent implements
            AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            mLocation.setText(arr[arg2]);
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            mLocation.setText("");
        }
    }

    private void endDatePickerDialog() {


        mBtnEndday.setOnClickListener(new View.OnClickListener() {
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
                        strBuf.append(month+1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);


                        mEndday.setText(strBuf.toString());
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
        mBtnStartday.setOnClickListener(new View.OnClickListener() {
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

                        mStartday.setText(strBuf.toString());
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
            for (DataSnapshot rentSnapshot :dataSnapshot.getChildren()) {
            RentManagers list=rentSnapshot.getValue(RentManagers.class);
                rentSnapshot.getKey();
            RentManagers rentManager=new RentManagers();
                assert list != null;
                String location=list.getLocation();
            String startday=list.getStartdate();
            String enddate=list.getEnddate();
            String price=list.getPrice();
            String intro=list.getIntro();
            String urlim=list.getUrl();
            rentManager.setLocation(location);
            rentManager.setStartdate(startday);
            rentManager.setEnddate(enddate);
            rentManager.setPrice(price);
            rentManager.setUrl(urlim);
            rentManager.setIntro(intro);
            rentManagers.add(rentManager);
            }
                rentCarAdapter = new RentCarAdapter(this, rentManagers, getApplicationContext(), new RentCarAdapter.Action() {
                    @Override
                    public void onClickItem(RentManagers manager, int position) {
                      Intent intent=new Intent(RentCar.this, SelectItem.class);
                        intent.putExtra("rentManager", manager);
                      startActivity(intent);
                    }

                    @Override
                    public void onLongClickItem(RentManagers manager, int position) {

                    }
                });
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RentCar.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(rentCarAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

