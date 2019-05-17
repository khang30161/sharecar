package com.example.khang.sharecar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartRent extends AppCompatActivity {
    private Button mNeedrent, mWantRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_rent);
        mNeedrent=findViewById(R.id.need_to_rent);
        mWantRent=findViewById(R.id.want_to_rent);

        mWantRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartRent.this, WantRentActivity.class);
                startActivity(intent);

            }
        });
    }
}
