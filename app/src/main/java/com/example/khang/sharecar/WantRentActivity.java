package com.example.khang.sharecar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class WantRentActivity extends AppCompatActivity {
    String arr[]={
            "Hà Nội",
            "Thành Phố Hồ CHí Minh",
            "Hải Phòng",
            "Cần Thơ","Đà Nẵng", "Bà rịa - Vũng Tàu", "Long An", "Quãng Ninh", "Đồng Nai", "Bình Dương"};
    TextView selection;
    public static final String LOCATION ="LOCATION";
    public static final String PRICE ="PRICE";
    public static final String STARTDATE ="STARTDATE";
    public static final String ENDDATE ="ENDDATE";
    public static final String BUNDLE ="BUNDLE";

    TextView mLocation,  mStartdate, mEnddate;
    EditText mPrice;
    Button finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_rent);
        mLocation=findViewById(R.id.selection);
        mEnddate=findViewById(R.id.endday);
        mStartdate=findViewById(R.id.startday);
        mPrice=findViewById(R.id.price);
        finish=findViewById(R.id.want_rent_finish_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location=mLocation.getText().toString();
                String enddate=mEnddate.getText().toString();
                String startdate=mStartdate.getText().toString();
                String price=mPrice.getText().toString();
                byBundle(location,enddate,startdate,price);
            }
        });

        this.showDatePickerDialog();
        this.endDatePickerDialog();
        //Lấy đối tượng Spinner ra
        Spinner spin=findViewById(R.id.spinner);
        selection=findViewById(R.id.selection);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());
    }

    private void byBundle(String location, String enddate, String startdate, String price) {
        Intent intent = new Intent(WantRentActivity.this, RentCar.class);
        Bundle bundle = new Bundle();
        bundle.putString(LOCATION, location);
        bundle.putString(STARTDATE, startdate);
        bundle.putString(ENDDATE, enddate);
        bundle.putString(PRICE, price);
        intent.putExtra(BUNDLE, bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void endDatePickerDialog() {
        Button endDatePickerDialogButton = (Button)findViewById(R.id.endDatePickerDialogButton);
        endDatePickerDialogButton.setOnClickListener(new View.OnClickListener() {
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

                        TextView datePickerValueTextView = (TextView)findViewById(R.id.endday);
                        datePickerValueTextView.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(WantRentActivity.this, onDateSetListener, year, month, day);



                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }


    private void showDatePickerDialog() {
        Button datePickerDialogButton = (Button)findViewById(R.id.datePickerDialogButton);
        datePickerDialogButton.setOnClickListener(new View.OnClickListener() {
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

                        TextView datePickerValueTextView = (TextView)findViewById(R.id.startday);
                        datePickerValueTextView.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(WantRentActivity.this, onDateSetListener, year, month, day);



                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }


    private class MyProcessEvent implements
                AdapterView.OnItemSelectedListener
{
    //Khi có chọn lựa thì vào hàm này
    public void onItemSelected(AdapterView<?> arg0,
                               View arg1,
                               int arg2,
                               long arg3) {
        //arg2 là phần tử được chọn trong data source
        selection.setText(arr[arg2]);
    }
    //Nếu không chọn gì cả
    public void onNothingSelected(AdapterView<?> arg0) {
        selection.setText("");
    }
}
}
