package com.example.john.afdb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    public int getPic(String icon){
        switch(icon){
            case "m9":
                return R.drawable.m9;
            case "colt":
                return R.drawable.colt;
            case "mandp":
                return R.drawable.mandp;
            case "mossberg500":
                return R.drawable.mossberg_500;
            case "six86":
                return R.drawable.six86;
            case "winchester94":
                return R.drawable.winchester_94;
            case "e11":
                return R.drawable.e11_blaster;
            case "bfg":
                return R.drawable.bfg;
            case "hiPoint":
                return R.drawable.hi_point;
            case "mini":
                return R.drawable.mini_gun;
            case "python":
                return R.drawable.python;
            default:
                return 0;
        }
    }
    private void setPic(String mCurrentPhotoPath) {

        ImageView mImageView = (ImageView)findViewById(R.id.bang_image);

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        mImageView.setImageBitmap(bitmap);
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

        final ShakeListener mShaker = new ShakeListener(this);

        final DatabaseHelper helper = DatabaseHelper.getInstance(bangActivity.this);

        ImageView gunImage = (ImageView) findViewById(R.id.bang_image);

        String pic  = helper.getPicById(id);

        int i = getPic(pic);

        if (i == 0){
            setPic(pic);
        }else {
            gunImage.setImageResource(i);
        }

//        Intent i = new Intent(CreateActivity.this, MainActivity.class);
//
//        startActivity(i);



        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {

                String sound = helper.getSoundByID(id);
                int soundPlay = getSound(sound);

                playSound(bangActivity.this, soundPlay);

                Toast.makeText(bangActivity.this, "Bang", Toast.LENGTH_SHORT).show();

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
