package com.example.john.afdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final TextView title = (TextView) findViewById(R.id.title);

        Intent i = getIntent();

        final String titleText = i.getStringExtra("type");


        title.setText("This view will show " + titleText + "s!!");

    }
}
