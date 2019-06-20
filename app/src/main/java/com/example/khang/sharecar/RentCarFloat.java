package com.example.khang.sharecar;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RentCarFloat extends AppCompatActivity {
    Button mXemay, mOto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car_float);
        mOto = (Button) findViewById(R.id.oto);
        mXemay = (Button) findViewById(R.id.xemay);
        mXemay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new XemayFragment());

            }
        });
// perform setOnClickListener event on Second Button
        mOto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                loadFragment(new OtoFragment());
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout1, fragment)
                    .commit();
        }
    }
}
