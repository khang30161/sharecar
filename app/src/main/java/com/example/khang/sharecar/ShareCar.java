package com.example.khang.sharecar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShareCar extends AppCompatActivity {
    private Button mDriver, mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_car);
        mCustomer=findViewById(R.id.customer);
        mDriver=findViewById(R.id.driver);

        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShareCar.this, ShareCarCustomer.class);
                startActivity(intent);
                finish();
            }
        });
        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShareCar.this, ShareCarDriverSumary.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
