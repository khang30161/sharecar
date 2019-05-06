package com.example.khang.sharecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton mSharecar, mRentcar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                            Intent intent=new Intent(MainActivity.this, DriverMapActivity.class);
                            startActivity(intent);
                            finish();
                       
                    return true;
                case R.id.navigation_dashboard:
                    mSharecar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MainActivity.this, DriverMapActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    return true;
                case R.id.navigation_notifications:
                    mSharecar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MainActivity.this, DriverMapActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRentcar=findViewById(R.id.main_act_rentcar);
        mSharecar=findViewById(R.id.main_act_sharecar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }

}




