package com.example.john.afdb;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ListActivity extends AppCompatActivity {
    private ListView mGunListView;
    private CursorAdapter mCursorAdapter;
    private CursorAdapter mCursorAdapter2;
    private DatabaseHelper mHelper;
    public String titleText = "Search Results";
    public Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DatabaseHelper db = new DatabaseHelper(this);

        Intent i = getIntent();

        mGunListView = (ListView)findViewById(R.id.list_view);
        mHelper = new DatabaseHelper(ListActivity.this);

        try{

            titleText = i.getStringExtra("type").toString();
             cursor = mHelper.getGunByType(titleText);



        }
        catch(RuntimeException e){

            Toast.makeText(ListActivity.this, "H4X", Toast.LENGTH_SHORT).show();
            cursor = mHelper.getGunList();



        }
            //Do stuff with intent data here

            ((TextView) findViewById(R.id.title2)).setText(titleText.toString().toUpperCase());








        mCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{DatabaseHelper.COL_ITEM_BRAND, DatabaseHelper.COL_ITEM_MODEL},new int[]{android.R.id.text1, android.R.id.text2},0);

        mGunListView.setAdapter(mCursorAdapter);


        handleIntent(getIntent());

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

// Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = DatabaseHelper.getInstance(ListActivity.this).searchAll(query);
            mCursorAdapter.changeCursor(cursor);
            mCursorAdapter.notifyDataSetChanged();
        }
    }
}
