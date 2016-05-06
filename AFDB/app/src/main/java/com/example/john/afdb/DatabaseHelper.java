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
    public static final String GUN_LIST_TABLE_NAME2 = "GUN_LIST2";

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
    public static final String COL_ITEM_SOUND = "SOUND";

    public static final String COL_ITEM_THING = "THING";
    public static final String GUN_ID = "GUNID";


    public static final String[] GUN_COLUMNS = {COL_ID,COL_ITEM_BRAND,COL_ITEM_MODEL,COL_ITEM_CALIBER,COL_ITEM_TYPE, COL_ITEM_SOUND};

    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_TYPE,COL_ITEM_BRAND,COL_ITEM_MODEL,COL_ITEM_CALIBER};

    private static final String CREATE_GUN_LIST_TABLE2 =
            "CREATE TABLE " + GUN_LIST_TABLE_NAME2 +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GUN_ID + "INTEGER" +
                    COL_ITEM_THING + ")";


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
                    COL_ITEM_SOUND + " TEXT, "+
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
        //COL_ITEM_BRAND  COL_ITEM_MODEL  + COL_ITEM_FINISH + COL_ITEM_WOOD + COL_ITEM_CALIBER + COL_ITEM_SERIAL + COL_ITEM_TYPE  + COL_ITEM_STAR  + COL_ITEM_PICTURE ;

        db.execSQL(CREATE_GUN_LIST_TABLE2);
        db.execSQL(CREATE_GUN_LIST_TABLE);

        insert(db, 1, "Colt", "1911", "Blued", 1, ".45", "12345", "pistol", 3, "colt", "pistol");
        insert(db, 2, "Smith and Wesson", "686", "Chrome", 0, ".357", "12345", "revolver", 5, "six86", "revolver");
        insert(db, 3, "Mossberg", "500", "Blued", 1, "12 Ga", "12345", "shotgun", 4, "mossberg500", "shotgun");
        insert(db, 4, "Winchester", "Lever", "blued", 1, "30-30", "12345", "rifle", 4, "winchester94","rifle");
        insert(db, 5, "Beretta", "M9", "Blued", 0, "9mm", "12345", "pistol", 4, "m9", "pistol");
        insert(db, 6, "Smith and Wesson", "M&P 9", "Blued", 0, "9mm", "12345", "pistol", 4, "mandp", "pistol");
        insert(db, 7, "E11", "Blaster", "Blued", 0, "Laser", "12345", "rifle", 2, "e11", "pew");
        insert(db, 8, "Doom", "BFG", "Blued", 0, "Laser", "12345", "shotgun", 3, "bfg", "pew");
        insert(db, 9, "Hi-Point", "C9", "Blued", 0, "9mm", "12345", "pistol", 1, "hiPoint", "pistol");
        insert(db, 10, "Mini", "Gun", "Blued", 0, "7.62 X 51 Nato", "12345", "rifle", 2, "mini", "rifle");
        insert(db, 11, "Colt", "Python", "Blued", 1, ".357 Magnum", "12345", "revolver", 2, "python", "revolver");


//e11 bfg hiPoint mini python


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GUN_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public void insert(SQLiteDatabase database, int id, String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture, String sound) {
        // Get a reference to the database
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
        values.put(COL_ITEM_SOUND, sound);


        database.insert(GUN_LIST_TABLE_NAME, null, values);
    }

       public void insert2(SQLiteDatabase database, int id, String brand, int gunId){
        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(GUN_ID, gunId);
        values.put(COL_ITEM_THING, brand);


        database.insert(GUN_LIST_TABLE_NAME2, null, values);
    }


    public boolean updateStar(int star, int id) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(COL_ITEM_STAR, star);

        return database.update(GUN_LIST_TABLE_NAME, args, COL_ID + "=" + id, null) > 0;
    }


    public long addItem(String brand, String model, String finish, int wood, String caliber, String serial, String type, int star, String picture, String sound) {

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
        values.put(COL_ITEM_SOUND, sound);


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
    public String getSoundByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME,
                new String[]{COL_ITEM_SOUND},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_SOUND));
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

    public Cursor getGunByStar(String query){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME, // a. table
                GUN_COLUMNS, // b. column names
                COL_ITEM_STAR + " LIKE ?", // c. selections
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

    //use this method to search all by brand name
    public Cursor searchAll(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GUN_LIST_TABLE_NAME, // a. table
                GUN_COLUMNS, // b. column names
                 COL_ITEM_BRAND + " LIKE ? OR " + COL_ITEM_MODEL + " LIKE ? OR " + COL_ITEM_CALIBER + " LIKE ? ", // c. selections
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
}

