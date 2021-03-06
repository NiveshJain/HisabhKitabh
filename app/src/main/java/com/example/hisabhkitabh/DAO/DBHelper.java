package com.example.hisabhkitabh.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LNJPC on 08-02-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hisabh_kitabh.db";
    private  static DBHelper sDBHelper ;

    public static synchronized DBHelper getInstance (Context context){

        if(sDBHelper == null) {
            sDBHelper = new DBHelper(context.getApplicationContext());
        }
        return sDBHelper;
    }

    private DBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + Contract.Users.TABLE_NAME + " (" +
                Contract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.Users.COLUMN_FIRST_NAME + " TEXT NOT NULL," +
                Contract.Users.COLUMN_LAST_NAME + " TEXT  NULL," +
                Contract.Users.COLUMN_CONTACT_NUMBER + " INTEGER " +
                ");" ;

        final String SQL_CREATE_Event_PARTICIPANTS_TABLE = "CREATE TABLE "
                + Contract.EventPraticipants.TABLE_NAME + " (" +
                Contract.EventPraticipants.COLUMN_TID + " INTEGER ," +
                Contract.EventPraticipants.COLUMN_FROM + " TEXT NOT NULL," +
                Contract.EventPraticipants.COLUMN_TO + " TEXT NOT NULL," +
                Contract.EventPraticipants.COLUMN_AMOUNT + " REAL NOT NULL, " +
                " FOREIGN KEY" +"(" + Contract.EventPraticipants.COLUMN_TID + ")" +
                " REFERENCES "  + Contract.Event.TABLE_NAME +"(" + Contract.Event._ID +
                "), " + " FOREIGN KEY " + "(" + Contract.EventPraticipants.COLUMN_FROM + ")" +
                " REFERENCES " + Contract.Users.TABLE_NAME + "(" + Contract.Users._ID + "));" ;

        final String SQL_CREATE_Event_TABLE = "CREATE TABLE "
                + Contract.Event.TABLE_NAME + " (" +
                Contract.Event._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.Event.COLUMN_DATE + " INTEGER NOT NULL," +
                Contract.Event.COLUMN_DESCRIPTION + " TEXT NOT NULL" +
                ");" ;



        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_Event_TABLE);
        db.execSQL(SQL_CREATE_Event_PARTICIPANTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
