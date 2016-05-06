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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {


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
    private void setPic(String mCurrentPhotoPath) {

        ImageView mImageView = (ImageView)findViewById(R.id.gun_pic);

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
        setContentView(R.layout.activity_detail);


        final DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
        Button deleteButton = (Button)findViewById(R.id.delete_button);
        Button updateButton = (Button)findViewById(R.id.update_button);
        Button playButton = (Button)findViewById(R.id.soundButton);
        Button bangButton = (Button)findViewById(R.id.bang_button);


        final int id = getIntent().getIntExtra("id",-1);

        if(id >= 0){

            String brand = helper.getBrandById(id);
            String model= helper.getModelById(id);
            String finish = helper.getFinishById(id);

            //this will be a number so convert it
            String wood = helper.getWoodById(id);

            String caliber = helper.getCaliberById(id);
            String serial = helper.getSerialById(id);
            String type = helper.getTypeById(id);
            String star = helper.getStarById(id);
            String pic  = helper.getPicById(id);
            int i = getPic(pic);

            TextView finishTextView = (TextView)findViewById(R.id.finish_text);
            finishTextView.setText(finish);
            TextView brandTextView = (TextView)findViewById(R.id.brand_text);
            brandTextView.setText(brand);

            TextView modelTextView = (TextView)findViewById(R.id.model_text);
            modelTextView.setText(model);

            TextView caliberTextView = (TextView)findViewById(R.id.caliber_text);
            caliberTextView.setText(caliber);
            TextView serialTextView = (TextView)findViewById(R.id.serial_text);
            serialTextView.setText(serial);
            TextView typeTextView = (TextView)findViewById(R.id.type_text);
            typeTextView.setText(type);

            TextView woodTextView = (TextView)findViewById(R.id.wood_text);
            TextView starTextView = (TextView)findViewById(R.id.rating_text);
            ImageView gunImage = (ImageView) findViewById(R.id.gun_pic);

        if (i == 0){
            setPic(pic);
        }else {
            gunImage.setImageResource(i);
        }

            starTextView.setText(star);

            if (wood.equals("0")){
                woodTextView.setText("No Wood");
            }else{
                woodTextView.setText("Got Wood");
            }
        }

        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent i = new Intent(DetailActivity.this, MainActivity.class);

                startActivity(i);
                helper.delete(id);

                Toast.makeText(DetailActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner starSpin = (Spinner) findViewById(R.id.update_spinner);
//
                int starText = Integer.parseInt(starSpin.getSelectedItem().toString());

                helper.updateStar(starText, id);

                Intent intent = new Intent(DetailActivity.this, DetailActivity.class);

                intent.putExtra("id", id);
                startActivity(intent);

                Toast.makeText(DetailActivity.this, "Updated rating to " + starText + "!", Toast.LENGTH_SHORT).show();
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sound = helper.getSoundByID(id);
                int soundPlay = getSound(sound);

            playSound(DetailActivity.this, soundPlay);


            }
        });
        bangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, bangActivity.class);
                intent.putExtra("id", id);

                startActivity(intent);

            }
        });
    }
}
