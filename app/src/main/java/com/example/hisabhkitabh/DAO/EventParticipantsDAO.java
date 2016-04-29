package com.example.hisabhkitabh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hisabhkitabh.Model.EventParticipants;
import com.example.hisabhkitabh.Model.User;

import java.util.ArrayList;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class EventParticipantsDAO {

    private  EventParticipants mEventParticipants ;


    public long insertEventParticipants (EventParticipants eventParticipants, Context context){

        ContentValues participants = new ContentValues();

        participants.put(Contract.EventPraticipants.COLUMN_FROM,eventParticipants.getLender().getFirstName());
        participants.put(Contract.EventPraticipants.COLUMN_TO,eventParticipants.getBorrower().getFirstName());
        participants.put(Contract.EventPraticipants.COLUMN_AMOUNT,eventParticipants.getAmount());


        SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
        return db.insertWithOnConflict(Contract.EventPraticipants.TABLE_NAME,null,participants,SQLiteDatabase.CONFLICT_IGNORE);
    }

    public ArrayList<EventParticipants> getAllTransactions (Context context){

        ArrayList<EventParticipants> arrayList = new ArrayList<EventParticipants> ();

        String sql = "select " + Contract.EventPraticipants.COLUMN_TO + "," +
                Contract.EventPraticipants.COLUMN_FROM + "," +
                "total"+"("+Contract.EventPraticipants.COLUMN_AMOUNT+") "+
                "from "+ Contract.EventPraticipants.TABLE_NAME + " group by "+
                Contract.EventPraticipants.COLUMN_TO+","+
                Contract.EventPraticipants.COLUMN_FROM+";";

        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        Cursor cursor  = db.rawQuery(sql,null);

        while(cursor.getCount() > 0 && cursor.moveToNext()){

       String BORROWER [] =  cursor.getString(cursor.getColumnIndex(Contract.EventPraticipants.COLUMN_TO)).split(" ");
       String LENDER [] =    cursor.getString(cursor.getColumnIndex(Contract.EventPraticipants.COLUMN_FROM)).split(" ");
       Double AMOUNT =  (cursor.getDouble(cursor.getColumnIndex("total"+"("+Contract.EventPraticipants.COLUMN_AMOUNT+")")));

        mEventParticipants =   new EventParticipants(new User(LENDER[0],null,0),new User(BORROWER[0], null,0),AMOUNT);
            arrayList.add(mEventParticipants);

        }

        cursor.close();
        return arrayList;
}
}

