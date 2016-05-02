package com.example.john.afdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 5/1/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Guns.db";


    // Define SQL statements to create and delete guns table
    public static final String SQL_CREATE_GUN_TABLE =
            "CREATE TABLE guns ( id INTEGER PRIMARY KEY, brand TEXT, model TEXT, finish TEXT, wood INTEGER, caliber TEXT, serial TEXT, type TEXT, star INTEGER, picture BLOB )";

    public static final String SQL_DROP_GUN_TABLE = "DROP TABLE IF EXISTS guns";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the guns table when the database is created
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GUN_TABLE);
    }

    // When the database is upgraded, the old data isn't needed. Delete the guns
    // table and recreate the table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_GUN_TABLE);
        onCreate(db);
    }

    public void insert(int id, String brand, String model, String finsih, int wood, String caliber, String serial, String type, int star, byte[] picture){
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("brand", brand);
        values.put("model", model);
        values.put("caliber", caliber);
        values.put("serial", serial);
        values.put("type", type);
        values.put("star", star);
        values.put("serial", serial);
        values.put("picture", picture);



        // Insert the row into the guns table
        db.insert("guns", null, values);
    }

    public Gun getGunById(int id){
        // Get a reference to the database
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection, which tells the query to return only the columns mentioned
        // similar to "SELECT column1, column2, column3"
        String[] projection = new String[]{ "id", "brand", "model", "caliber", "serial", "type", "star", "serial", "picture" };

        // Define a selection, which defines the WHERE clause of the query (but not the values for it)
        // similar to "WHERE x < 23", only without the value; "WHERE x < ?"
        String selection = "id = ?";

        // Define the selection values. The ?'s in the selection
        // The number of values in the following array should equal the number of ? in the where clause
        String[] selectionArgs = new String[]{ String.valueOf(id) };

        // Make the query, getting the cursor object
        Cursor cursor = db.query("guns", projection, selection, selectionArgs, null, null, null, null);

        // With the cursor, create a new gun object and return it
        cursor.moveToFirst();

        String name = cursor.getString( cursor.getColumnIndex("brand") );
        String year = cursor.getString( cursor.getColumnIndex("model") );

        return new Gun (id, name, year);
    }
}

// Need to create gun class. Also need to add delete functionality. Read the week 4 day 4 readme. Where adding this cusom class left off
