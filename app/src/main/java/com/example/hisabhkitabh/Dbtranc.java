package com.example.hisabhkitabh;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.hisabhkitabh.dbmodel.Contract;
import com.example.hisabhkitabh.dbmodel.DBHelper;

/**
 * Created by LNJPC on 11-02-2016.
 */
public class Dbtranc extends AsyncTask<Object , String, String>  {

    private DBHelper database ;
    private SQLiteDatabase db  ;

    @Override
    protected String doInBackground(Object... params) {

        String [] fullname = ((String)params[0]).split(" ");
        ContentValues userDetails =  new ContentValues (2);
        userDetails.put(Contract.Users.COLUMN_FIRST_NAME, fullname[0]);
        userDetails.put(Contract.Users.COLUMN_LAST_NAME,fullname [1]);
        userDetails.put(Contract.Users.COLUMN_CONTACT_NUMBER,Integer.valueOf((String)params[1]));

        database = new DBHelper((Context) params[2]);
        db = database.getWritableDatabase();
        return "0";
    }


}
