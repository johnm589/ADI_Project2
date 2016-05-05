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
    private DatabaseHelper mHelper;
    public String titleText = "Search Result";
    private Cursor cursor;
    private Cursor searchCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mGunListView = (ListView)findViewById(R.id.list_view);
        mHelper = new DatabaseHelper(ListActivity.this);

        if (!Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            Intent i = getIntent();
            if (i.getStringExtra("type") != null){
            titleText = i.getStringExtra("type").toString();

                cursor = mHelper.getGunByType(titleText);

                Toast.makeText(ListActivity.this, "Showing Type Results", Toast.LENGTH_SHORT).show();
                ((TextView) findViewById(R.id.title2)).setText(titleText.toString().toUpperCase() + "S");

            }else{
                titleText = i.getStringExtra("star").toString();

                cursor = mHelper.getGunByStar(titleText);

                Toast.makeText(ListActivity.this, "Showing Star Results", Toast.LENGTH_SHORT).show();
                ((TextView) findViewById(R.id.title2)).setText(titleText.toString().toUpperCase() + " Stars");

            }
        }

        handleIntent(getIntent());



        final Cursor whichCursor = null == cursor ? searchCursor : cursor;

        mCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,whichCursor,new String[]{DatabaseHelper.COL_ITEM_BRAND, DatabaseHelper.COL_ITEM_MODEL},new int[]{android.R.id.text1, android.R.id.text2},0);

        mGunListView.setAdapter(mCursorAdapter);

        mGunListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent intent = new Intent(ListActivity.this, DetailActivity.class);


                Cursor cursor = mCursorAdapter.getCursor();
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
            searchCursor = DatabaseHelper.getInstance(ListActivity.this).searchAll(query);
            if(mCursorAdapter != null) {
                mCursorAdapter.swapCursor(searchCursor);
                mCursorAdapter.notifyDataSetChanged();
            }
        }
    }
}
