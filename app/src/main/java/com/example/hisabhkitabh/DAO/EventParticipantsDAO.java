package com.example.hisabhkitabh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hisabhkitabh.Model.EventParticipants;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class EventParticipantsDAO {

    public long insertEventParticipants (EventParticipants eventParticipants, Context context){

        ContentValues participants = new ContentValues();
        participants.put(Contract.EventPraticipants.COLUMN_FROM,eventParticipants.getLender().getFirstName()+eventParticipants.getLender().getLastName());
        participants.put(Contract.EventPraticipants.COLUMN_TO,eventParticipants.getBorrower().getFirstName());
        participants.put(Contract.EventPraticipants.COLUMN_AMOUNT,eventParticipants.getAmount());


        SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
        return db.insertWithOnConflict(Contract.Event.TABLE_NAME,null,participants,SQLiteDatabase.CONFLICT_IGNORE);
    }

}
