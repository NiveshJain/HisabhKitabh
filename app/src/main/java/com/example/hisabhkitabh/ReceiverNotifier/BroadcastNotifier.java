package com.example.hisabhkitabh.ReceiverNotifier;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by LNJPC on 19-03-2016.
 */
public class BroadcastNotifier  {

    public static final String BROADCAST_ACTION = "com.example.hisabhkitabh.Receiver.BROADCAST_ACTION" ;
    public static final String EXTENDED_DATA_STATUS ="com.example.hisabhkitabh.Receiver.STATUS" ;
    private LocalBroadcastManager mBroadcaster ;

    public BroadcastNotifier (Context context){
        mBroadcaster = LocalBroadcastManager.getInstance(context);
    }


    public void broadcastIntentWithState (int status) {

        if(status == 0 ) {
            Intent localIntent = new Intent();

            // The Intent contains the custom broadcast action for this app
            localIntent.setAction(BROADCAST_ACTION);

            // Puts the status into the Intent
            localIntent.putExtra("EXTENDED_DATA_STATUS",status);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);

            // Broadcasts the Intent
            mBroadcaster.sendBroadcast(localIntent);
        }
    }
}
