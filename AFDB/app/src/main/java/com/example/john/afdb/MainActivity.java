package com.example.john.afdb;

import android.content.Intent;
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

        //HardCode DB
        DatabaseHelper db = new DatabaseHelper(this);
//        int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture
        db.insert(1, "Colt", "1911", "Chrome", 0, ".45", "12345", "pistol", 3, "");
        db.insert(2, "Smith and Wesson", "686", "Chrome", 1, ".357", "12345", "revolver", 5, "");
        db.insert(3, "Mossberg", "500", "Blued", 0, "12 Ga", "12345", "shotgun", 4, "");
        db.insert(4, "Winchester", "Lever", "blued", 1, "30-30", "12345", "rifle", 4, "");




        revolverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("type", "revolver");

                startActivity(i);
            }
        });
        pistolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("type", "pistol");

                startActivity(i);


            }
        });        rifleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("type", "rifle");

                startActivity(i);



            }
        });        shotgunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("type", "shotgun");

                startActivity(i);
            }
        });

    }
}
