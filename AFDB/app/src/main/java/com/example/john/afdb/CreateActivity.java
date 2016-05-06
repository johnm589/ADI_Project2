package com.example.john.afdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CreateActivity extends AppCompatActivity {

    String mCurrentPhotoPath;

    public File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "JPEG_" + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();


        return image;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(CreateActivity.this, "No SD card on this device", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


            }
        }
    }
    private void setPic() {
        ImageView mImageView = (ImageView)findViewById(R.id.imageThumb);

        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        mImageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final DatabaseHelper helper = DatabaseHelper.getInstance(CreateActivity.this);


        Button picButton = (Button)findViewById(R.id.pic);
        Button submitButton = (Button)findViewById(R.id.submit);


        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText brandEdit = (EditText)findViewById(R.id.brandET);
                 String mTextBrandEdit = brandEdit.getText().toString();
                EditText modelEdit = (EditText)findViewById(R.id.modelET);
                 String mTextModelEdit = modelEdit.getText().toString();

                EditText finishEdit = (EditText)findViewById(R.id.finishET);
                 String mTextFinishEdit = finishEdit.getText().toString();

                EditText caliberEdit = (EditText)findViewById(R.id.caliberET);
                 String mTextCaliber = caliberEdit.getText().toString();


                EditText serialEdit = (EditText)findViewById(R.id.serialET);
                 String mTextSerial = serialEdit.getText().toString();


                Spinner typeSpin = (Spinner)findViewById(R.id.typeET);
//
                String typeText = typeSpin.getSelectedItem().toString();

                Spinner soundSpin = (Spinner)findViewById(R.id.soundET);

                String soundText = soundSpin.getSelectedItem().toString();

                Spinner starSpin = (Spinner)findViewById(R.id.starET);

                int starText = Integer.parseInt(starSpin.getSelectedItem().toString());

                Spinner woodSpin = (Spinner)findViewById(R.id.woodET);

                String woodSpinText = woodSpin.getSelectedItem().toString();

                    int woodSpinInt;

                    if (woodSpinText.equals("Yes")){
                        woodSpinInt = 1;
                    }else{
                        woodSpinInt = 0;
                }

                helper.addItem(mTextBrandEdit,mTextModelEdit,mTextFinishEdit,woodSpinInt,mTextCaliber,mTextSerial,typeText,starText,mCurrentPhotoPath,soundText);

                Intent i = new Intent(CreateActivity.this, MainActivity.class);

                startActivity(i);

                Toast.makeText(CreateActivity.this, "Entry Created", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
