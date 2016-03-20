package com.example.hisabhkitabh.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hisabhkitabh.DAO.UserDAO;
import com.example.hisabhkitabh.Model.User;
import com.example.hisabhkitabh.R;

/**
 * Created by LNJPC on 10-02-2016.
 */
public class SignInActivity extends AppCompatActivity {

private  ProgressDialog mProgressDialog ;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        mContext = this;
        mProgressDialog = new ProgressDialog(this);
    }

    public void onSignInClick(View view) {

         Intent home_intent ;

        showprogress();

        EditText username = (EditText) (view.getRootView()).findViewById(R.id.Username);
        final String [] fullnameArray =  (username.getText().toString()).split(" ");

        EditText contact =  (EditText)(view.getRootView()).findViewById(R.id.Contact_no);
        final long number =  Long.parseLong((contact.getText().toString().trim()));

        home_intent = new Intent ();
        home_intent.putExtra("fullname",fullnameArray);
        home_intent.putExtra("number",number);

        if(fullnameArray != null && number >9 ) {


            final User firstUser = new User(fullnameArray[0], fullnameArray[1], number);
            final UserDAO userDAO = new UserDAO();
            new Thread(new Runnable(){
                @Override
                public void run() {
                    long rowId =   userDAO.insertUser(firstUser,mContext);
                         mProgressDialog.dismiss();
                    // implement the code for failure in insertion
                }
            }).start();

            //passing the result back to HomeActivity
            setResult(RESULT_OK, home_intent);
            finish();
        }
        else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private void showprogress() {
        mProgressDialog.setTitle("Signing In...");
        mProgressDialog.show();
    }


    //closing the application on Cancel
    public void onCancelClick(View view) {
        Toast exitToast =  Toast.makeText(getApplicationContext(), "Closing Application", Toast.LENGTH_SHORT);
        exitToast.setDuration(Toast.LENGTH_SHORT);
        exitToast.show();
         finish();

    }
}
