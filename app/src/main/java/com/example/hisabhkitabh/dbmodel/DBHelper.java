package com.example.hisabhkitabh.dbmodel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LNJPC on 08-02-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hisabh_kitabh.db";

    public DBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + Contract.Users.TABLE_NAME + " (" +
                Contract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.Users.COLUMN_FIRST_NAME + " TEXT NOT NULL," +
                Contract.Users.COLUMN_LAST_NAME + " TEXT NOT NULL," +
                Contract.Users.COLUMN_CONTACT_NUMBER + " INTEGER NOT NULL UNIQUE" +
                ");" ;

        final String SQL_CREATE_TRANSACTION_PARTICIPANTS_TABLE = "CREATE TABLE "
                + Contract.TransactionPraticipants.TABLE_NAME + " (" +
                Contract.TransactionPraticipants.COLUMN_TID + " INTEGER," +
                Contract.TransactionPraticipants.COLUMN_FROM + " TEXT NOT NULL," +
                Contract.TransactionPraticipants.COLUMN_TO + " TEXT NOT NULL," +
                Contract.TransactionPraticipants.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                " FOREIGN KEY " +"(" + Contract.TransactionPraticipants.COLUMN_TID + ")" +
                " REFERENCES " + Contract.Transaction.TABLE_NAME + " (" + Contract.Transaction._ID +
                ")" + " FOREIGN KEY " + "(" + Contract.TransactionPraticipants.COLUMN_FROM + ")" +
                " REFERENCES " + Contract.Users.TABLE_NAME + " (" + Contract.Users._ID + ");" ;

        final String SQL_CREATE_TRANSACTION_TABLE = "CREATE TABLE "
                + Contract.Transaction.TABLE_NAME + " (" +
                Contract.Transaction._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.Transaction.COLUMN_DATE + " INTEGER NOT NULL," +
                Contract.Transaction.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                ");" ;



        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
        db.execSQL(SQL_CREATE_TRANSACTION_PARTICIPANTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
