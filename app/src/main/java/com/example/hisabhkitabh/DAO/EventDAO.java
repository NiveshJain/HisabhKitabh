package com.example.hisabhkitabh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hisabhkitabh.Model.Event;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class EventDAO {

    public long insertEvent(Event event,Context context) {

        ContentValues eventValues = new ContentValues();
        eventValues.put(Contract.Event.COLUMN_DATE,event.getDate().toString());
        eventValues.put(Contract.Event.COLUMN_DESCRIPTION,event.getDescription());

       SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
        return db.insertWithOnConflict(Contract.Event.TABLE_NAME,null,eventValues,SQLiteDatabase.CONFLICT_IGNORE);
    }
}
