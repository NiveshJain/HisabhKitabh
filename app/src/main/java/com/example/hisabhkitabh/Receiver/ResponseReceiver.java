package com.example.hisabhkitabh.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by LNJPC on 19-03-2016.
 */
public class ResponseReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getIntExtra("EXTENDED_DATA_STATUS",0)== 0){

        }
    }
}
