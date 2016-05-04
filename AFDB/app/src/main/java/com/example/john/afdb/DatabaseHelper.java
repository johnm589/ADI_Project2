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
    public static final String DATABASE_NAME = "Guns_DB2";
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

    public static final String[] GUN_COLUMNS = {COL_ID,COL_ITEM_BRAND,COL_ITEM_MODEL,COL_ITEM_CALIBER,COL_ITEM_TYPE};

    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_TYPE,COL_ITEM_BRAND,COL_ITEM_MODEL,COL_ITEM_CALIBER};

//    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_BRAND,COL_ITEM_MODEL,COL_ITEM_FINISH,COL_ITEM_WOOD,COL_ITEM_CALIBER,COL_ITEM_SERIAL,COL_ITEM_TYPE,COL_ITEM_STAR,COL_ITEM_PICTURE};
    //"CREATE TABLE guns ( id INTEGER PRIMARY KEY, brand TEXT, model TEXT, finish TEXT, wood INTEGER,
    // caliber TEXT, serial TEXT, type TEXT, star INTEGER, picInt INTEGER, picText TEXT )";


    private static final String CREATE_GUN_LIST_TABLE =
            "CREATE TABLE " + GUN_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_BRAND + " TEXT, " +
                    COL_ITEM_MODEL + " TEXT, " +
                    COL_ITEM_FINISH + " TEXT, " +
                    COL_ITEM_WOOD + " INTEGER, " +
                    COL_ITEM_CALIBER + " TEXT, " +
                    COL_ITEM_SERIAL + " TEXT, " +
                    COL_ITEM_TYPE + " TEXT, " +
                    COL_ITEM_STAR + " INTEGER, "+
                    COL_ITEM_PICTURE + " TEXT )";

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

        insert(db, 1, "Colt", "1911", "Chrome", 0, ".45", "12345", "pistol", 3, "colt");
        insert(db, 2, "Smith and Wesson", "686", "Chrome", 1, ".357", "12345", "revolver", 5, "six86");
        insert(db, 3, "Mossberg", "500", "Blued", 0, "12 Ga", "12345", "shotgun", 4, "mossberg500");
        insert(db, 4, "Winchester", "Lever", "blued", 1, "30-30", "12345", "rifle", 4, "winchester94");
        insert(db, 5, "Beretta", "M9", "Blued", 0, "9mm", "12345", "pistol", 4, "m9");
        insert(db, 6, "Smith and Wesson", "M&P 9", "Blued", 0, "9mm", "12345", "pistol", 4, "mandp");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GUN_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public void insert(SQLiteDatabase database, int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture) {
        // Get a reference to the database
//        SQLiteDatabase database = getWritableDatabase();


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

        database.insert(GUN_LIST_TABLE_NAME, null, values);
    }
//

    public long addItem(String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture) {

        ContentValues values = new ContentValues();

        values.put(COL_ITEM_BRAND, brand);
        values.put(COL_ITEM_MODEL, model);
        values.put(COL_ITEM_FINISH, finish);
        values.put(COL_ITEM_WOOD, wood);
        values.put(COL_ITEM_CALIBER, caliber);
        values.put(COL_ITEM_SERIAL, serial);
        values.put(COL_ITEM_STAR, star);
        values.put(COL_ITEM_PICTURE, picture);
        values.put(COL_ITEM_TYPE, type);

        SQLiteDatabase database = getWritableDatabase();

        long returnId = database.insert(GUN_LIST_TABLE_NAME, null, values);

        database.close();

        return returnId;
    }



    public String getFinishById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_FINISH},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_FINISH));
        } else {
            return "No Description Found";
        }
    }
    public String getCaliberById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_CALIBER},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_CALIBER));
        } else {
            return "No Description Found";
        }
    }

    public String getSerialById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_SERIAL},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_SERIAL));
        } else {
            return "No Description Found";
        }
    }
    public String getWoodById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_WOOD},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_WOOD));
        } else {
            return "No Description Found";
        }
    }
    public String getTypeById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_TYPE},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_TYPE));
        } else {
            return "No Description Found";
        }
    }
    public String getStarById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_STAR},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_STAR));
        } else {
            return "No Description Found";
        }
    }
    public String getBrandById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_BRAND},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
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
    public Cursor getGunByType(String query){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME, // a. table
                GUN_COLUMNS, // b. column names
                COL_ITEM_TYPE + " LIKE ?", // c. selections
                new String[]{ query }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
    public String getModelById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_MODEL},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_MODEL));
        } else {
            return "No Description Found";
        }
    }
    public String getPicById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_PICTURE},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_PICTURE));
        } else {
            return "No Description Found";
        }
    }

    public int getPic(String icon){
        switch(icon){
            case "m9":
                return R.drawable.m9;
            case "colt":
                return R.drawable.colt;
            case "mandp":
                return R.drawable.mandp;
            case "mossberg500":
                return R.drawable.mossberg_500;
            case "six86":
                return R.drawable.six86;
            case "winchester94":
                return R.drawable.winchester_94;
            default:
                return 0;
        }
    }

    public void delete(int id){
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // Define the selection, or the where
        String selection = COL_ID +" = ?";

        // Define the selection values. The ?'s in the selection
        // The number of values in the following array should equal the number of ? in the where clause
        String[] selectionArgs = new String[]{ String.valueOf(id) };

        // Delete everything that satisfies the selection
        db.delete(GUN_LIST_TABLE_NAME, selection, selectionArgs);
    }

    //use this method to seach all by brand name
    public Cursor searchAll(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME, // a. table
                GUN_COLUMNS, // b. column names
                 COL_ITEM_BRAND + " LIKE ?", // c. selections
                new String[]{"%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
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

