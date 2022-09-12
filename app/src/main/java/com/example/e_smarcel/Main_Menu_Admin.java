package com.example.e_smarcel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main_Menu_Admin extends AppCompatActivity implements View.OnClickListener {

    private CardView scanner, history, parcelstatus, whatApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_admin);

        scanner = (CardView) findViewById(R.id.scanner_card);
        history = (CardView) findViewById(R.id.history_card);
        parcelstatus = (CardView) findViewById(R.id.parcelstatus_card);
        whatApps = (CardView) findViewById(R.id.whatApps_card);

        scanner.setOnClickListener((View.OnClickListener) this);
        history.setOnClickListener((View.OnClickListener) this);
        parcelstatus.setOnClickListener((View.OnClickListener) this);
        whatApps.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()) {
            case R.id.scanner_card:
                i = new Intent(this, Qr_Scanner.class);
                startActivity(i);
                break;

            case R.id.history_card:
                i = new Intent(this, choose_courier_history.class);
                startActivity(i);
                break;

            case R.id.parcelstatus_card:
                i = new Intent(this, Parcel_Status.class);
                startActivity(i);
                break;

            case R.id.whatApps_card:
                i = new Intent(this, whatApps.class);
                startActivity(i);
                break;
        }
    }

}
