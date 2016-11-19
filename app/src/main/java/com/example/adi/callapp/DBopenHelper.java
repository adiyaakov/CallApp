package com.example.adi.callapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adi on 19/11/2016.
 */
public class DBopenHelper extends SQLiteOpenHelper {
    public static final String TABLE_CONTACTS = "Contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_IMAGE_URI = "imageUri";
    public static final String COLUMN_EMAIL = "email";


    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_LASTNAME + " TEXT, " +
            COLUMN_PHONE + " TEXT, " +
            COLUMN_IMAGE_URI + " TEXT," +
            COLUMN_EMAIL + " TEXT" +
            ");";

    private static final String DB_NAME = "Database.db";
    private static final int DB_VERSION = 1;

    public DBopenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
