package com.example.khang.sharecar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class WantRentActivity extends AppCompatActivity {
    String arr[]={
            "Hà Nội",
            "Thành Phố Hồ CHí Minh",
            "Hải Phòng",
            "Cần Thơ","Đà Nẵng", "Bà rịa - Vũng Tàu", "Long An", "Quãng Ninh", "Đồng Nai", "Bình Dương"};
    TextView selection;
    FirebaseStorage storage;
    StorageReference storageReference;

    TextView mLocation,  mStartdate, mEnddate;
    EditText mPrice, mIntro;
    Button finish;
    FirebaseAnalytics mFirebaseAnalytics;
    DatabaseReference databaseReference;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    ImageView mPictureRent;
    Button mAddPic;
    public static String FB_STORAGE_PATH="image/";
    public static String FB_DATABASE_PATH="post";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_rent);

        databaseReference=FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mLocation=findViewById(R.id.selection);
        mEnddate=findViewById(R.id.endday);
        mStartdate=findViewById(R.id.startday);
        mPrice=findViewById(R.id.price);
        mIntro=findViewById(R.id.et_intro);
        finish=findViewById(R.id.want_rent_finish_button);
        mPictureRent=findViewById(R.id.add_pic_rent);
        mAddPic=findViewById(R.id.btn_add_pic);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();




            }

        });
        mAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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

    private void loadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." +getImageExt(filePath));
            UploadTask uploadTask = ref.putFile(filePath);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri taskResult = task.getResult();


                                    String location=mLocation.getText().toString();
                                    String enddate=mEnddate.getText().toString();
                                    String startdate=mStartdate.getText().toString();
                                    String price=mPrice.getText().toString();
                                    String intro=mIntro.getText().toString();
                                    String abc =taskResult.toString();
                              if(!TextUtils.isEmpty(location)&& !TextUtils.isEmpty(startdate)){
                                        String id=databaseReference.push().getKey();
                                        RentManagers rentManager=new RentManagers();
                                        rentManager.setLocation(location);
                                        rentManager.setStartdate(startdate);
                                        rentManager.setEnddate(enddate);
                                        rentManager.setPrice(price);
                                        rentManager.setIntro(intro);
                                       rentManager.setUrl(abc);
                                        databaseReference.child(id).setValue(rentManager);
                                        mLocation.setText("");
                                        mEnddate.setText("");
                                        mStartdate.setText("");
                                        mPrice.setText("");

                                    }



                            progressDialog.dismiss();

                        Toast.makeText(WantRentActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(WantRentActivity.this, RentCar.class);
                        startActivity(intent);
                        finish();

                        }
                    }});


        }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mPictureRent.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public String getImageExt (Uri uri){
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
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
            selection.setText(arr[arg2]);
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            selection.setText("");
        }
    }
}