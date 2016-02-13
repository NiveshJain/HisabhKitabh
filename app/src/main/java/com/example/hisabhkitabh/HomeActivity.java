package com.example.hisabhkitabh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class HomeActivity extends FragmentActivity {


    SharedPreferences prefs = null;

    private  static String  firstuser = null;
    private  static String contact_no = null;

    private static  int SIGN_IN_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        prefs =  getSharedPreferences("com.example.hisabhkitabh",MODE_PRIVATE);
        // Determines if the app is ran for the first time
        if (prefs.getBoolean("newInstallation", true)) {

            startActivityForResult(new Intent(this, SignInActivity.class),SIGN_IN_REQUEST_CODE );

            prefs.edit().putBoolean("newInstallation", false).commit();


        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.distribution,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

       }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode== SIGN_IN_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                firstuser =  data.getStringExtra("name");
                 contact_no = data.getStringExtra("contact_no");
                ( (TextView) findViewById(R.id.username_home)).setText(firstuser);

                //new Dbtranc().doInBackground(firstuser,contact_no,getApplicationContext());
            }
            else
                this.finish();
        }


    }


    public void showList (View namesView) {


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}



