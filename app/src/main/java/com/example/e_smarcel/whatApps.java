package com.example.e_smarcel;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class whatApps extends AppCompatActivity {

    //your message
    private static final String message = "Salam Sejahtera Tuan/Puan, dimaklumkam barangan pos/parcel tuan/puan dalam simpanan pihak Koop Parcel. Pengambilan boleh dibuat pada hari ini di Koop Parcel dari jam 12.00 tengahari sehingga jam 6.00 petang sahaja.\n" +
            "\n" +
            "Terima kasih. ➡️" +
            "" +
            " Note : koperasi parcel berada di cafe admin, sebelah sinki di bangunan pentadbiran" +
            "Sila tunjukkan tracking id parcel anda kepada pihak koop parcel.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_apps);

        final AppCompatButton sendText = findViewById(R.id.sendTextBtn);
        final AppCompatButton sendImageText = findViewById(R.id.sendImgTextBtn);

        sendImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //choose image from gallery to send
                chooseImageFromGallery();
            }
        });

        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating send intent
                Intent intent = new Intent(Intent.ACTION_SEND);

                //add message along with intent
                intent.putExtra(Intent.EXTRA_TEXT,message);

                //set message type whatsapp accept only plain text
                intent.setType("text/plain");

                //whatsapp package name so intent can identify which app to open
                intent.setPackage("com.whatsapp");

                //start activity
                startActivity(intent);

            }
        });
    }

    private void chooseImageFromGallery(){

        //open image chooser to choose an image
        activityResultLauncher.launch("image/*");
    }

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {

            //onActivityResult is called when an image is choosed and result contains image's uri

            //creating send intent
            Intent intent = new Intent(Intent.ACTION_SEND);

            //add message along with intent
            intent.putExtra(Intent.EXTRA_TEXT,message);

            //add image(Uri) along with intent
            intent.putExtra(Intent.EXTRA_STREAM,result);

            //set message type whatsapp accept only plain text
            intent.setType("text/plain");

            //set image type
            intent.setType("image/jpeg");

            //whatsapp package name so intent can identify which app to open
            intent.setPackage("com.whatsapp");

            //start activity
            startActivity(intent);
        }
    });
}