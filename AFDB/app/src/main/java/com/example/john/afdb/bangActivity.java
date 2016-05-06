package com.example.john.afdb;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class bangActivity extends AppCompatActivity {

    public int getSound(String icon){
        switch(icon){
            case "pistol":
                return R.raw.pistol_sound;
            case "revolver":
                return R.raw.revolver_sound;
            case "shotgun":
                return R.raw.shotgun_sound;
            case "rifle":
                return R.raw.rifle_sound;
            case "pew":
                return R.raw.laser_sound;
            default:
                return 0;
        }
    }

    private static void playSound(Context context, int soundID){
        MediaPlayer mp = MediaPlayer.create(context, soundID);
        mp.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang);

        final int id = getIntent().getIntExtra("id",-1);

        Button homeButton = (Button)findViewById(R.id.home);



        final DatabaseHelper helper = DatabaseHelper.getInstance(bangActivity.this);

//        Intent i = new Intent(CreateActivity.this, MainActivity.class);
//
//        startActivity(i);


       final ShakeListener mShaker = new ShakeListener(this);

        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {

                String sound = helper.getSoundByID(id);
                int soundPlay = getSound(sound);

                playSound(bangActivity.this, soundPlay);

            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShaker.setOnShakeListener(null);
                Intent i = new Intent(bangActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
