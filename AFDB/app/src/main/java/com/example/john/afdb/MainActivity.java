package com.example.john.afdb;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
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
        Button createButton = (Button)findViewById(R.id.createButton);
        ImageView fiveStar = (ImageView)findViewById(R.id.fiveStar);
        ImageView fourStar = (ImageView)findViewById(R.id.fourStar);
        ImageView threeStar = (ImageView)findViewById(R.id.threeStar);
        ImageView twoStar = (ImageView)findViewById(R.id.twostar);
        ImageView oneStar = (ImageView)findViewById(R.id.oneStar);

        fiveStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("star", "5");

                startActivity(i);
            }
        });
        fourStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("star", "4");

                startActivity(i);
            }
        });
        threeStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("star", "3");

                startActivity(i);
            }
        });
        twoStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("star", "2");

                startActivity(i);
            }
        });
        oneStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                i.putExtra("star", "1");

                startActivity(i);
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(i);

            }
        });
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

// Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(new ComponentName(this, ListActivity.class)));

        return true;
    }
}
