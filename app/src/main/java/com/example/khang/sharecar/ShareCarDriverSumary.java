package com.example.khang.sharecar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khang.sharecar.Fragment.hcm_map;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class ShareCarDriverSumary extends AppCompatActivity {
    public static String FB_STORAGE_PATH = "image/";
    public static String FB_DATABASE_PATH = "post";
    private final int PICK_IMAGE_REQUEST = 71;
    String arr[] = {
            "Hà Nội",
            "TP.Hồ Chí Minh",
            "Hải Phòng",
            "Cần Thơ", "Đà Nẵng", "Bà rịa - Vũng Tàu", "Long An", "Quãng Ninh", "Đồng Nai", "Bình Dương"};
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAnalytics mFirebaseAnalytics;
    DatabaseReference databaseReference;
    String prefname = "my_data";
    private Button mStartdate, finish;
    private TextView mTvStartdate;
    private RadioButton mMin, mMax, mSeat;
    private EditText mIntro, mTvStarttimeH, mTvStarttimeM, mTvEndtimeH, mTvEndtimeP;
    private TextView mStylecar;
    private ImageView mToyota, mFord, mBwm, mMercedes, mVinfast, mHonda, mLambo, mKia;
    private EditText mDongxe;
    private Spinner mSpinner, mSpinnerDistrict, mSpinnerWard;
    private EditText mStart, mEnd;
    private Button mSummary;
    private Button mAdd;
    private ImageView mPhoto;
    private Uri filePath1;
    private TextView Price;
    private JSONArray dataLocation;
    private JSONArray districtListSelect;
    private JSONArray wardListSelect;
    private String citySelected, districtSelected, wardSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_car_driver_sumary);
        readJsonLocation();
        mStartdate = findViewById(R.id.btn_starttime);
        mTvStartdate = findViewById(R.id.tv_startdate);
        mTvStarttimeH = findViewById(R.id.tv_starttimehour);
        mTvStarttimeM = findViewById(R.id.tv_starttimeminute);
        Price = findViewById(R.id.priceshare);
        mTvEndtimeH = findViewById(R.id.ev_endtimehour);
        mTvEndtimeP = findViewById(R.id.ev_endtimemi);

        mSpinner = findViewById(R.id.spinner_share);
        mSpinnerDistrict = findViewById(R.id.spinner_district);
        mSpinnerWard=findViewById(R.id.spinner_war);

        mStart = findViewById(R.id.et_start);
        mEnd = findViewById(R.id.et_finish);
        mAdd = findViewById(R.id.btn_add_photo_share);
        mPhoto = findViewById(R.id.imv_photo_share);
        mMin = findViewById(R.id.rb_min);
        mMax = findViewById(R.id.rb_max);
        mIntro = findViewById(R.id.et_note_share);
        mStylecar = findViewById(R.id.stylecar);
        mSeat = findViewById(R.id.cho34);
        mToyota = findViewById(R.id.toyota);
        mMercedes = findViewById(R.id.merce);
        mVinfast = findViewById(R.id.vinf);
        mKia = findViewById(R.id.kia);
        mLambo = findViewById(R.id.lambo);
        mHonda = findViewById(R.id.honda);
        mBwm = findViewById(R.id.bwm);
        mFord = findViewById(R.id.ford);
        mDongxe = findViewById(R.id.dongxe);
        finish = findViewById(R.id.finish123);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Share").child(FB_DATABASE_PATH);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        showDatePickerDialog();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });

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
        //thiết lập sự kiện chọn phần tử cho Spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        citySelected=mSpinner.getSelectedItem().toString();
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseimage();
                int giobd = Integer.parseInt(mTvStarttimeH.getText() + "");
                int phutbd = Integer.parseInt(mTvStarttimeM.getText() + "");
                int giokt = Integer.parseInt(mTvEndtimeH.getText() + "");
                int phutkt = Integer.parseInt(mTvEndtimeP.getText() + "");
                Price.setText((((giokt + phutkt / 60) - (giobd + phutbd / 60)) * 50 * 10000) + "");
            }

        });


        mToyota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Toyota");
            }
        });
        mMercedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Mercedes");
            }
        });
        mKia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Kia");
            }
        });
        mVinfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Vinfast");
            }
        });
        mFord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Ford");
            }
        });
        mBwm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Bwm");
            }
        });
        mHonda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Honda");
            }
        });
        mLambo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStylecar.setText("Lamborghini");
            }
        });

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
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

    private void chooseimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


    }

    private void loadImage() {
        if (filePath1 != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final StorageReference reference = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(filePath1));
            UploadTask uploadTask = reference.putFile(filePath1);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri taskResult = task.getResult();
                        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
                        //String quan = pre.getString("quan", "");
                        String quan=mSpinnerDistrict.getSelectedItem().toString();
                        String phuong=mSpinnerWard.getSelectedItem().toString();
                        String startdate = mTvStartdate.getText().toString();
                        int starttimehour = Integer.parseInt(mTvStarttimeH.getText().toString());
                        int starttimemi = Integer.parseInt(mTvStarttimeM.getText().toString());
                        int endtimehour = Integer.parseInt(mTvEndtimeH.getText().toString());
                        int endtimemi = Integer.parseInt(mTvEndtimeP.getText().toString());

                        String seat = (mSeat.isChecked() == true) ? "4" : "6";
                        String loaixe = mStylecar.getText().toString();
                        String dongxe = mDongxe.getText().toString();
                        String soghetrong = (mMin.isChecked() == true) ? "3-4 chỗ" : "4-6 chỗ";
                        String gioithieu = mIntro.getText().toString();
                        String price = Price.getText().toString();
                        String diembatdau = mStart.getText().toString();
                        String diemketthuc = mEnd.getText().toString();
                        String abc = taskResult.toString();
                        if (!TextUtils.isEmpty(diembatdau) && !TextUtils.isEmpty(startdate) && !TextUtils.isEmpty(diemketthuc) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(gioithieu) && !TextUtils.isEmpty(loaixe) && !TextUtils.isEmpty(dongxe) && !TextUtils.isEmpty(soghetrong) && !TextUtils.isEmpty(seat)) {
                            String id = databaseReference.push().getKey();
                            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            ShareManager shareManagers = new ShareManager();
                            shareManagers.setStartday(startdate);
                            shareManagers.setStarttimeh(starttimehour);
                            shareManagers.setStarttimem(starttimemi);
                            shareManagers.setEndtimeh(endtimehour);
                            shareManagers.setEndtimem(endtimemi);
                            shareManagers.setLocalcity(citySelected);
                            shareManagers.setLocalquan(quan);
                            shareManagers.setLocalphuong(phuong);
                            shareManagers.setSeat(seat);
                            shareManagers.setLoaixe(loaixe);
                            shareManagers.setIntro(dongxe);
                            shareManagers.setSovitri(soghetrong);
                            shareManagers.setUrl(abc);
                            shareManagers.setPrice(price);
                            shareManagers.setStartpoint(diembatdau);
                            shareManagers.setEndpoint(diemketthuc);
                            shareManagers.setUid(userid);
                            shareManagers.setIdShare(id);

                            //shareManagers.setIsbooking1("");
                            databaseReference.child(id).setValue(shareManagers);

                            mStartdate.setText("");


                        }


                        progressDialog.dismiss();

                        Toast.makeText(ShareCarDriverSumary.this, "Uploaded", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(ShareCarDriverSumary.this, ShareCar.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });


        }
    }

    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                mPhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();

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
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(ShareCarDriverSumary.this, onDateSetListener, year, month, day);


                // Popup the dialog.
                datePickerDialog.show();
            }
        });

    }


}
