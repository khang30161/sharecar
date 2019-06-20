package com.example.khang.sharecar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AddProduct extends Fragment {

    Button mAddpro;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_product, container, false);
        mAddpro=view.findViewById(R.id.addproduct);

        mAddpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent  intent=new Intent(getActivity(), RentCarFloat.class);
                        startActivity(intent);
                        return;

                    }
                });


        return view;
    }


}
