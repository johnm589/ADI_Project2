package com.example.john.afdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DatabaseHelper db = new DatabaseHelper(this);
//        int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture

        Intent i = getIntent();

        String titleText = i.getStringExtra("type").toString();

        Gun retrievedGame = db.getGunByType(titleText);

        ((TextView)findViewById(R.id.title2)).setText(retrievedGame.toString());



//        ListView listView = (ListView)findViewById(R.id.list_view);
//
//        DatabaseHelper helper = DatabaseHelper.getInstance(ListActivity.this);
//
//        final Cursor cursor = helper.getExampleList();
//
//        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(ListActivity.this,android.R.layout.simple_list_item_1,cursor,new String[]{DatabaseHelper.COL_ITEM_BRAND},new int[]{android.R.id.text1},0);
//
//        listView.setAdapter(simpleCursorAdapter);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                cursor.moveToPosition(position);
//                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(ExampleSQLiteOpenHelper.COL_ID)));
//                startActivity(intent);
//            }
//        });





    }
}
