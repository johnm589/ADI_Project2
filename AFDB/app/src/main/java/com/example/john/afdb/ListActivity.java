package com.example.john.afdb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class ListActivity extends AppCompatActivity {
    private ListView mGunListView;
    private CursorAdapter mCursorAdapter;
    private CursorAdapter mCursorAdapter2;
    private DatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DatabaseHelper db = new DatabaseHelper(this);
//        int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture

        Intent i = getIntent();

        String titleText = i.getStringExtra("type").toString();

        ((TextView)findViewById(R.id.title2)).setText(titleText.toString().toUpperCase());



        mGunListView = (ListView)findViewById(R.id.list_view);
        mHelper = new DatabaseHelper(ListActivity.this);

        final Cursor cursor = mHelper.getGunByType(titleText);

        mCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{DatabaseHelper.COL_ITEM_BRAND},new int[]{android.R.id.text1},0);

        mGunListView.setAdapter(mCursorAdapter);


//        handleIntent(getIntent());


        mGunListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID)));
                startActivity(intent);
            }
        });





    }
}
