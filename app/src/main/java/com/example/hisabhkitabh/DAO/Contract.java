package com.example.hisabhkitabh.DAO;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by LNJPC on 08-02-2016.
 */
public class Contract  {


    public static final String CONTENT_AUTHORITY = "com.example.hisabhkitabh";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //table name for users
    public static final String PATH_USERS = "users";

    //table name for participants involved in event
    public static final String PATH_EVENT_PARTICIPANTS = "event_participants";

    //table name for details associtated with event
    public static final String PATH_EVENT = "event" ;


    // Class representing Table users
    public static final class Users implements BaseColumns {

        //content uri for table users
        public  static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USERS).build();

        public  static final String TABLE_NAME = "users";

        public  static final String _ID = "user_id";

        public  static final String COLUMN_FIRST_NAME = "first_name";

        public  static final String COLUMN_LAST_NAME = "last_name";

        public static final String COLUMN_CONTACT_NUMBER = "contact_number";

    }

    // Class representing Table event_participants
    public static final class EventPraticipants implements BaseColumns {

        //content uri for table users
        public  static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT_PARTICIPANTS).build();

        public  static final String TABLE_NAME = "event_participants";

         public  static final String COLUMN_TID = "event_id";

        public  static final String COLUMN_FROM = "_from";

        public  static final String COLUMN_TO = "_to";

        public static final String COLUMN_AMOUNT = "amount";

    }


    // Class representing Table event_participants
    public static final class Event implements BaseColumns {

        //content uri for table event
        public  static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        public  static final String TABLE_NAME = "event";

        public  static final String COLUMN_TID = "event_id";

        public  static final String COLUMN_DATE = "date";

        public  static final String COLUMN_DESCRIPTION = "description";



    }

}
