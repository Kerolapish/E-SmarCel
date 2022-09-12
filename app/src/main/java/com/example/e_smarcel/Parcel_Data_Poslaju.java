package com.example.e_smarcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.renderscript.ScriptGroup;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_smarcel.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Parcel_Data_Poslaju extends AppCompatActivity {

    //declaration variable for scanner
    private EditText txtTrack, txtKey, txtDate;
    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture;

    //declaration variable for date picker
    Button  save, datePicker, back;
    EditText date;
    DatePickerDialog datePickerDialog;
    int year, month, dayOffMonth;
    Calendar calendar;

    //declaration variable for database
    ActivityMainBinding binding;
    String trackNum,specKey;
    Date date1;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceStance){
        super.onCreate(savedInstanceStance);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_parcel_data_poslaju);

        //define variable for save
        save = (Button) findViewById(R.id.btnSave);
        txtKey = (EditText) findViewById(R.id.txtKey) ;
        txtDate = (EditText) findViewById(R.id.txtDate);

        //define variable for scanner
        previewView = findViewById(R.id.cameraPreview);
        txtTrack = (EditText) findViewById(R.id.txtTrack);

        //checking for camera permission
        if (ContextCompat.checkSelfPermission(Parcel_Data_Poslaju.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            init();
        }
        else{
            ActivityCompat.requestPermissions(Parcel_Data_Poslaju.this, new String[]{Manifest.permission.CAMERA},101);
        }

        //function button datePicker
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog
                datePickerDialog = new DatePickerDialog(Parcel_Data_Poslaju.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        //function for save data in database
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set string for specKey, date and trackNum
                String specKey = txtKey.getText().toString();
                String date = txtDate.getText().toString();
                String trackNum = txtTrack.getText().toString();

                //function for insert data in database
                if(!trackNum.isEmpty() && !specKey.isEmpty() && !date.isEmpty()){
                    POSLAJU pl = new POSLAJU(trackNum, specKey, date);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference().child("admins").child("courierPoslaju");
                    reference.child(trackNum).setValue(pl).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            txtTrack.setText("");
                            txtKey.setText("");
                            txtDate.setText("");

                            Toast.makeText(Parcel_Data_Poslaju.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }

    //create class for function scanner
    public void init(){
        cameraProviderListenableFuture = ProcessCameraProvider.getInstance(Parcel_Data_Poslaju.this);

        cameraProviderListenableFuture.addListener(new Runnable() {
            @Override
            public void run() {

                try {
                    ProcessCameraProvider cameraProvider = cameraProviderListenableFuture.get();
                    bindImageAnalysis(cameraProvider);

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, ContextCompat.getMainExecutor(Parcel_Data_Poslaju.this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            init();
        }
        else{
            Toast.makeText(Parcel_Data_Poslaju.this, "Permissions Denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindImageAnalysis(ProcessCameraProvider processCameraProvider){

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(Parcel_Data_Poslaju.this), new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy image) {
                Image mediaImage = image.getImage();

                if (mediaImage!=null){
                    InputImage image2 = InputImage.fromMediaImage(mediaImage, image.getImageInfo().getRotationDegrees());

                    BarcodeScanner scanner = BarcodeScanning.getClient();

                    Task<List<Barcode>> results = scanner.process(image2);

                    results.addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                        @Override
                        public void onSuccess(List<Barcode> barcodes) {

                            for (Barcode barcode : barcodes){
                                final String getValue = barcode.getRawValue();

                                txtTrack.setText(getValue);
                            }

                            image.close();
                            mediaImage.close();
                        }
                    });
                }
            }
        });

        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        processCameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview);
    }
}