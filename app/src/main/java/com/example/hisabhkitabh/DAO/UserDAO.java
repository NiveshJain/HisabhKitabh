package com.example.hisabhkitabh.DAO;

import android.content.Context;
import android.database.Cursor;

import com.example.hisabhkitabh.Model.User;

import java.util.ArrayList;

/**
 * Created by LNJPC on 23-02-2016.
 */
public class UserDAO {


    public void insertUser(User user){

        String insertUserQuery = "INSERT ";

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
                    user.setContactNumber(cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_CONTACT_NUMBER)));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();

        return userList;
    }

}
