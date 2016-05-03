package com.example.john.afdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText idEdit = (EditText)findViewById(R.id.idET);
        EditText brandEdit = (EditText)findViewById(R.id.brandET);
        EditText modelEdit = (EditText)findViewById(R.id.modelET);
        EditText finishEdit = (EditText)findViewById(R.id.finishET);

        Spinner woodSpin = (Spinner)findViewById(R.id.woodET);

        EditText caliberEdit = (EditText)findViewById(R.id.caliberET);
        EditText serialEdit = (EditText)findViewById(R.id.serialET);

        Spinner typeSpin = (Spinner)findViewById(R.id.typeET);

        Spinner starEdit = (Spinner)findViewById(R.id.starET);




    }
}
