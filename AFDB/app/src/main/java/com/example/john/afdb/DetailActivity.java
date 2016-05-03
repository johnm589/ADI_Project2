package com.example.john.afdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
        Button deleteButton = (Button)findViewById(R.id.delete_button);


        final int id = getIntent().getIntExtra("id",-1);

        if(id >= 0){

            String brand = helper.getBrandById(id);
            String model= helper.getModelById(id);
            String finish = helper.getFinishById(id);

            //this will be a number so convert it
            String wood = helper.getWoodById(id);

            String caliber = helper.getCaliberById(id);
            String serial = helper.getCaliberById(id);
            String type = helper.getTypeById(id);
            String star = helper.getStarById(id);

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
                return true;
            }
        });

    }


}
