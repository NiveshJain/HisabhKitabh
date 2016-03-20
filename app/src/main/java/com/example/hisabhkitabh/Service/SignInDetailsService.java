package com.example.hisabhkitabh.Service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.example.hisabhkitabh.DAO.Contract;
import com.example.hisabhkitabh.DAO.DBHelper;
import com.example.hisabhkitabh.ReceiverNotifier.BroadcastNotifier;

/**
 * Created by LNJPC on 19-03-2016.
 */
public class SignInDetailsService extends IntentService {

    private BroadcastNotifier mBroadcaster = new BroadcastNotifier(this.getApplicationContext());

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SignInDetailsService(String name) {
        super(name);
    }

    public SignInDetailsService (){
        super("SignInDetailsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int STATE_ACTION_COMPLETE = 0;
        int STATE_ACTION_INCOMPLETE = 1;

        String fullname = intent.getStringExtra("fullname");
        long contact_no = intent.getLongExtra("number", 0);

        ContentValues userValues = new ContentValues();
        userValues.put(Contract.Users.COLUMN_FIRST_NAME,fullname.split(" ")[0]);
        userValues.put(Contract.Users.COLUMN_LAST_NAME, fullname.split(" ")[1]);
        userValues.put(Contract.Users.COLUMN_CONTACT_NUMBER, contact_no);

      SQLiteDatabase db =  DBHelper.getInstance(this).getWritableDatabase();
       long rowId =  db.insert(Contract.Users.TABLE_NAME,null,userValues);
        if (rowId != -1)
        mBroadcaster.broadcastIntentWithState(STATE_ACTION_COMPLETE);
        else {
            mBroadcaster.broadcastIntentWithState(STATE_ACTION_INCOMPLETE);
        }
    }
}
