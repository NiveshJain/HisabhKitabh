package com.example.hisabhkitabh.Service;

import android.app.IntentService;
import android.content.Intent;

import com.example.hisabhkitabh.DAO.UserDAO;
import com.example.hisabhkitabh.Model.User;
import com.example.hisabhkitabh.ReceiverNotifier.BroadcastNotifier;

/**
 * Created by LNJPC on 19-03-2016.
 */
public class SignInDetailsService extends IntentService {


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

        BroadcastNotifier mBroadcaster = new BroadcastNotifier(this.getApplicationContext());
        String  [] fullnameArray = intent.getStringExtra("fullname").split(" ");
        long contact_no = intent.getLongExtra("number", 0);

        User firstUser = new User(fullnameArray[0], fullnameArray[1], contact_no);
        UserDAO userDAO = new UserDAO();
        long rowId =   userDAO.insertUser(firstUser, this);

        if (rowId != -1)
        mBroadcaster.broadcastIntentWithState(STATE_ACTION_COMPLETE);
        else {
            mBroadcaster.broadcastIntentWithState(STATE_ACTION_INCOMPLETE);
        }
    }
}
