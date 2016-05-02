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

    private static final String TAG = DatabaseHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Guns_DB";
    public static final String GUN_LIST_TABLE_NAME = "GUN_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_BRAND = "BRAND";
    public static final String COL_ITEM_MODEL = "MODEL";
    public static final String COL_ITEM_FINISH = "DESCRIPTION";
    public static final String COL_ITEM_WOOD = "WOOD";
    public static final String COL_ITEM_CALIBER = "CALIBER";
    public static final String COL_ITEM_SERIAL = "SERIAL";
    public static final String COL_ITEM_TYPE = "TYPE";
    public static final String COL_ITEM_STAR = "STAR";
    public static final String COL_ITEM_PICTURE = "PICTURE";

    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_TYPE,COL_ITEM_BRAND,COL_ITEM_MODEL};

//    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_BRAND,COL_ITEM_MODEL,COL_ITEM_FINISH,COL_ITEM_WOOD,COL_ITEM_CALIBER,COL_ITEM_SERIAL,COL_ITEM_TYPE,COL_ITEM_STAR,COL_ITEM_PICTURE};
    //"CREATE TABLE guns ( id INTEGER PRIMARY KEY, brand TEXT, model TEXT, finish TEXT, wood INTEGER,
    // caliber TEXT, serial TEXT, type TEXT, star INTEGER, picInt INTEGER, picText TEXT )";


    private static final String CREATE_GUN_LIST_TABLE =
            "CREATE TABLE " + GUN_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_BRAND + " TEXT, " +
                    COL_ITEM_MODEL + " TEXT,  " +
                    COL_ITEM_FINISH + " TEXT, " +
                    COL_ITEM_WOOD + " INTEGER, " +
                    COL_ITEM_CALIBER + " TEXT, " +
                    COL_ITEM_SERIAL + " TEXT, " +
                    COL_ITEM_TYPE + " TEXT"+
                    COL_ITEM_STAR + " INTEGER, "+
                    COL_ITEM_PICTURE + " TEXT "+
                    ")";

    private static DatabaseHelper mInstance;

    public static DatabaseHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GUN_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GUN_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public void insert(int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture) {
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_ITEM_BRAND, brand);
        values.put(COL_ITEM_MODEL, model);
        values.put(COL_ITEM_FINISH, finish);
        values.put(COL_ITEM_WOOD, wood);
        values.put(COL_ITEM_CALIBER, caliber);
        values.put(COL_ITEM_SERIAL, serial);
        values.put(COL_ITEM_TYPE, type);
        values.put(COL_ITEM_STAR, star);
        values.put(COL_ITEM_PICTURE, picture);
    }

    public Cursor getExampleList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME, // a. table
                EXAMPLE_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public Gun getGunByType(String type){
        // Get a reference to the database
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection, which tells the query to return only the columns mentioned
        // similar to "SELECT column1, column2, column3"
        String[] projection = new String[]{ COL_ITEM_TYPE, COL_ITEM_BRAND, COL_ITEM_MODEL };

        // Define a selection, which defines the WHERE clause of the query (but not the values for it)
        // similar to "WHERE x < 23", only without the value; "WHERE x < ?"
        String selection = "type = ?";

        // Define the selection values. The ?'s in the selection
        // The number of values in the following array should equal the number of ? in the where clause
        String[] selectionArgs = new String[]{ String.valueOf(type) };

        // Make the query, getting the cursor object
        Cursor cursor = db.query(GUN_LIST_TABLE_NAME, projection, selection, selectionArgs, null, null, null, null);

        // With the cursor, create a new game object and return it
        cursor.moveToFirst();

        String brand = cursor.getString( cursor.getColumnIndex(COL_ITEM_BRAND) );
        String model = cursor.getString(cursor.getColumnIndex(COL_ITEM_MODEL));



        return new Gun(type, brand , model);
    }






    public String getDescriptionByType(String type){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_BRAND},
                COL_ITEM_TYPE+" = ?",
                new String[]{String.valueOf(type)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_BRAND));
        } else {
            return "No Description Found";
        }
    }










//
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "Guns.db";
//
//
//    // Define SQL statements to create and delete guns table
//    public static final String SQL_CREATE_GUN_TABLE =
//            "CREATE TABLE guns ( id INTEGER PRIMARY KEY, brand TEXT, model TEXT, finish TEXT, wood INTEGER, caliber TEXT, serial TEXT, type TEXT, star INTEGER, picInt INTEGER, picText TEXT )";
//
//    public static final String SQL_DROP_GUN_TABLE = "DROP TABLE IF EXISTS guns";
//
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Create the guns table when the database is created
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_GUN_TABLE);
//    }
//
//    // When the database is upgraded, the old data isn't needed. Delete the guns
//    // table and recreate the table
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DROP_GUN_TABLE);
//        onCreate(db);
//    }
//
//    public void insert(int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture){
//        // Get a reference to the database
//        SQLiteDatabase db = getWritableDatabase();
//
//        // create a new content value to store values
//        ContentValues values = new ContentValues();
//        values.put("id", id);
//        values.put("brand", brand);
//        values.put("model", model);
//        values.put("finish", finish);
//        values.put("wood", wood);
//        values.put("caliber", caliber);
//        values.put("serial", serial);
//        values.put("type", type);
//        values.put("star", star);
//        values.put("serial", serial);
//        values.put("picture",picture);
//
//
//
//        // Insert the row into the guns table
//        db.insert("guns", null, values);
//    }
////NEED TO MAKE GUN CLASS
//
//    public Gun getGunByName(String type){
//        // Get a reference to the database
//        SQLiteDatabase db = getReadableDatabase();
//
//        // Define a projection, which tells the query to return only the columns mentioned
//        // similar to "SELECT column1, column2, column3"
//        String[] projection = new String[]{ "id", "brand", "model", "finish", "wood", "caliber", "type", "star", "serial", "picture" };
//
//        // Define a selection, which defines the WHERE clause of the query (but not the values for it)
//        // similar to "WHERE x < 23", only without the value; "WHERE x < ?"
//        String selection = "name = ?";
//
//        // Define the selection values. The ?'s in the selection
//        // The number of values in the following array should equal the number of ? in the where clause
//        String[] selectionArgs = new String[]{ String.valueOf(type) };
//
//        // Make the query, getting the cursor object
//        Cursor cursor = db.query("guns", projection, selection, selectionArgs, null, null, null, null);
//
//        // With the cursor, create a new gun object and return it
//        cursor.moveToFirst();
//
//        String brand = cursor.getString( cursor.getColumnIndex("brand") );
//        String model = cursor.getString( cursor.getColumnIndex("model") );
//        String finish = cursor.getString(cursor.getColumnIndex("finish"));
//        int wood = cursor.getInt(cursor.getColumnIndex("wood"));
//        String caliber = cursor.getString(cursor.getColumnIndex("caliber"));
//        int star = cursor.getInt(cursor.getColumnIndex("star"));
//        String serial = cursor.getString(cursor.getColumnIndex("serial"));
//        String picture = cursor.getString(cursor.getColumnIndex("picture"));
//
//
////commented this out so id will only return brand and model
//       // , finish, wood, caliber, type, star, serial, picture
//
//
//        return new Gun (type, brand, model);
//    }
//
//    public void delete(int id){
//        // Get a reference to the database
//        SQLiteDatabase db = getWritableDatabase();
//
//        // Define the selection, or the where
//        String selection = "id = ?";
//
//        // Define the selection values. The ?'s in the selection
//        // The number of values in the following array should equal the number of ? in the where clause
//        String[] selectionArgs = new String[]{ String.valueOf(id) };
//
//        // Delete everything that satisfies the selection
//        db.delete("guns", selection, selectionArgs);
//    }

}

