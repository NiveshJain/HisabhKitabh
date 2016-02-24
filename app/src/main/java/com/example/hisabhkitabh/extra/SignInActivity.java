package com.example.hisabhkitabh.extra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hisabhkitabh.R;

/**
 * Created by LNJPC on 10-02-2016.
 */
public class SignInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    }

    public void onSignInClick(View view) {


         Intent home_intent ;

        EditText username = (EditText) (view.getRootView()).findViewById(R.id.Username);

        String fullname =  username.getText().toString();

        EditText contact =  (EditText)(view.getRootView()).findViewById(R.id.Contact_no);
        String number =  (contact.getText().toString().trim());

        home_intent = new Intent();
        home_intent.putExtra("name",fullname);
        home_intent.putExtra("contact_no", number);

        if(fullname != null && number.length()>9 )
            setResult(RESULT_OK, home_intent);
        else
            setResult(RESULT_CANCELED);
        finish ();
    }


    //closing the application on Cancel
    public void onCancelClick(View view) {

        Toast exitToast =  Toast.makeText(getApplicationContext(), "Closing Application", Toast.LENGTH_SHORT);
        exitToast.setDuration(Toast.LENGTH_SHORT);
        exitToast.show();
         finish();

    }
}
