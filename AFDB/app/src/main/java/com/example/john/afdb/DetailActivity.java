package com.example.john.afdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);

        TextView textViewDetail = (TextView)findViewById(R.id.description_text);


       final int id = getIntent().getIntExtra("id",-1);

        if(id >= 0){
            String description = helper.getDescriptionById(id);
            TextView textView = (TextView)findViewById(R.id.description_text);
            textView.setText(description);
        }

        textViewDetail.setOnLongClickListener(new View.OnLongClickListener() {
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
