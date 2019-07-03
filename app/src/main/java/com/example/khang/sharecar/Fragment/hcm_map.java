package com.example.khang.sharecar.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.khang.sharecar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;

public class hcm_map extends Fragment{
    private Spinner mSpinnerQuan, mSpinnerPhuong;
    private TextView mQuan, mPhuong;
    String prefname = "my_data";
    String arr1[]={
            "Quận 1","Quận 2","Quận 3","Quận 4","Quận 5","Quận 6","Quận 7","Quận 8","Quận 9","Quận 10","Quận 11","Quận 12",
            "Quận Bình Tân","Quận Bình Thạnh","Quận Gò Vấp","Quận Phú Nhuận","Quận Tân Phú","Quận Thủ Đức", "Huyện Bình Chánh", "Huyện Củ Chi", "Nhà Bè", "Hooc Môn"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hcm_map, container, false);
        mQuan=view.findViewById(R.id.tv_share_quan);
        mPhuong=view.findViewById(R.id.tv_share_phương);
        mSpinnerPhuong=view.findViewById(R.id.spinner_share_phương);
        mSpinnerQuan=view.findViewById(R.id.spinner_share_quan);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, arr1);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpinnerQuan.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinnerQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2,
                                       long arg3) {
                mQuan.setText(arr1[arg2]);

                String prefname = "my_data";
                SharedPreferences pre = getActivity().getSharedPreferences(prefname, MODE_PRIVATE);
                SharedPreferences.Editor editor = pre.edit();
                String quan = mQuan.getText().toString();


                editor.putString("quan", quan);

                editor.apply();

                String local=mQuan.getText().toString();
                if(local == "Thành Phố Hồ CHí Minh"){

                    //loadFragment(new hcm_map());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                mQuan.setText("");

            }

        });

        //Get Location TPHCM in Json.
        readJsonLocation();
        return view;
    }


    private String readJsonLocation(){

        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("tphcm.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.e("TEst", json);

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("data");

            Log.e("TEst", data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
