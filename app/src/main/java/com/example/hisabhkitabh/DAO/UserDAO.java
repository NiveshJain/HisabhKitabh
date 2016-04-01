package com.example.hisabhkitabh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hisabhkitabh.Model.User;

import java.util.ArrayList;

/**
 * Created by LNJPC on 23-02-2016.
 */
public class UserDAO {


    public long insertUser(User user,Context context){

        ContentValues userValues = new ContentValues();
        userValues.put(Contract.Users.COLUMN_FIRST_NAME,user.getFirstName());
        userValues.put(Contract.Users.COLUMN_LAST_NAME,user.getLastName());
        userValues.put(Contract.Users.COLUMN_CONTACT_NUMBER,user.getContactNumber());

        SQLiteDatabase db =  DBHelper.getInstance(context).getWritableDatabase();
        return db.insertWithOnConflict(Contract.Users.TABLE_NAME,null,userValues,SQLiteDatabase.CONFLICT_IGNORE);

    }

    public ArrayList<User> getAllUsers(Context context){
        ArrayList<User> userList = new ArrayList<User>();

        String fetchAllUsersQuery = "select * "  + " from " + Contract.Users.TABLE_NAME +";";
        DBHelper dbHelper = DBHelper.getInstance(context);
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(fetchAllUsersQuery,null);

            int count =  cursor.getCount();

            if(count>0 && cursor.moveToFirst()) {
                do {
                    User user = new User();
                 //   user.setUserId(cursor.getInt(cursor.getColumnIndex(Contract.Users._ID)));
                    user.setFirstName(cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_FIRST_NAME)));
                    user.setLastName(cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_LAST_NAME)));
                    user.setContactNumber(cursor.getLong(cursor.getColumnIndex(Contract.Users.COLUMN_CONTACT_NUMBER)));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();

        return userList;
    }

public User getFirstUser (Context context){
    User firstUser = new User();
    String sql = "SELECT * from " +
            Contract.Users.TABLE_NAME + " where " +
            Contract.Users._ID +
            "=1;";
    Cursor cursor =  DBHelper.getInstance(context).getReadableDatabase().rawQuery(sql,null);
    cursor.moveToFirst();
    firstUser.setFirstName(cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_FIRST_NAME)));
    firstUser.setLastName(cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_LAST_NAME)));
    firstUser.setContactNumber(Long.parseLong(cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_CONTACT_NUMBER))));
    return firstUser;
}

}
