package com.example.e_smarcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_smarcel.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Parcel_Recipient extends AppCompatActivity {

    ActivityMainBinding binding;
    String trackNum, name, numPhone, parcelStatus;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_parcel_recipient);
        Button submit = (Button) findViewById(R.id.btnSubmit);
        EditText txtTrack = (EditText) findViewById(R.id.txtTrack) ;
        EditText txtName = (EditText) findViewById(R.id.txtName) ;
        EditText txtNumPhone = (EditText) findViewById(R.id.txtNumPhone) ;
        //EditText txtStatus = (EditText) findViewById(R.id.txtStatus) ;

        //function button submit for store data in database
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trackNum = txtTrack.getText().toString();
                name = txtName.getText().toString();
                numPhone = txtNumPhone.getText().toString();
                //parcelStatus = txtStatus.getText().toString();

                if(!trackNum.isEmpty() && !name.isEmpty() && !numPhone.isEmpty()){

                    Recipient recipient = new Recipient(trackNum, name, numPhone, parcelStatus);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Recipient");
                    reference.child(trackNum).setValue(recipient).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            txtTrack.setText("");
                            txtName.setText("");
                            txtNumPhone.setText("");
                            //txtStatus.setText("");

                            Toast.makeText(Parcel_Recipient.this, "Successfully Inserted, You Will Get Notification via WhatsApps Once Your Parcel Arrived", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

