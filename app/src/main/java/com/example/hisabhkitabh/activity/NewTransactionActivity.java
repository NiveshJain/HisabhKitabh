package com.example.hisabhkitabh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.hisabhkitabh.R;

/**
 * Created by LNJPC on 19-02-2016.
 */
public class NewTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtransaction_layout);

       Toolbar toolbar =  (android.support.v7.widget.Toolbar) findViewById(R.id.transaction_toolbar);
        setSupportActionBar(toolbar);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newtransaction, menu);
        return true;
    }
}
