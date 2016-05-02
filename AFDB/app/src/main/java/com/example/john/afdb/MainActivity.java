package com.example.john.afdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView revolverButton = (ImageView)findViewById(R.id.revolver);
        ImageView pistolButton = (ImageView)findViewById(R.id.pistol);
        ImageView rifleButton = (ImageView)findViewById(R.id.rifle);
        ImageView shotgunButton = (ImageView)findViewById(R.id.shotgun);


        revolverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        pistolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });        rifleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });        shotgunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}
