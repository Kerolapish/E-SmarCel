package com.example.e_smarcel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class choose_courier_history extends AppCompatActivity {

    CardView abx, bestExpress, CityLink, dhl, flashExpress, Gdex, jnt,
            lineclear, nationwide, ninjavan, pegeon, poslaju, shopee, skynet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_courier_history);

        //define variable for courier
        abx = (CardView) findViewById(R.id.abx);
        bestExpress = (CardView) findViewById(R.id.bestExpress);
        CityLink = (CardView) findViewById(R.id.CityLink);
        dhl = (CardView) findViewById(R.id.dhl);
        flashExpress = (CardView) findViewById(R.id.flashExpress);
        Gdex = (CardView) findViewById(R.id.Gdex);
        jnt = (CardView) findViewById(R.id.jnt);
        lineclear = (CardView) findViewById(R.id.lineclear);
        nationwide = (CardView) findViewById(R.id.nationwide);
        ninjavan = (CardView) findViewById(R.id.ninjavan);
        pegeon = (CardView) findViewById(R.id.pegeon);
        poslaju = (CardView) findViewById(R.id.poslaju);
        shopee = (CardView) findViewById(R.id.shopee);
        skynet = (CardView) findViewById(R.id.skynet);

        //function for courier ABX
        abx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Best Express
        bestExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Best_Express.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier CityLink
        CityLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_CityLink.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Dhl
        dhl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Dhl.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Flash Express
        flashExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Flash_Express.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Gdex
        Gdex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Gdex.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier JnT
        jnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Jnt_Malaysia.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Line Clear
        lineclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Line_Clear.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Nation Wide
        nationwide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Nation_Wide.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Ninja Van
        ninjavan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Ninja_Van.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Pegeon
        pegeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Pegeon.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Poslaju
        poslaju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Poslaju.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Shoppee Express
        shopee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Shopee_Express.class);
                startActivity(gotoScanner);
            }
        });

        //function for courier Skynet
        skynet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoScanner = new Intent(getBaseContext(),History_Skynet.class);
                startActivity(gotoScanner);
            }
        });

    }
}
