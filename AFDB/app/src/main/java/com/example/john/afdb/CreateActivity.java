package com.example.john.afdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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


        Spinner starEdit = (Spinner)findViewById(R.id.starET);


        Button picButton = (Button)findViewById(R.id.pic);

        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner typeSpin = (Spinner)findViewById(R.id.typeET);

                String text = typeSpin.getSelectedItem().toString();

                Toast.makeText(CreateActivity.this, text, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
