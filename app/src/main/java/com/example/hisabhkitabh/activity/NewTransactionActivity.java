package com.example.hisabhkitabh.activity;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hisabhkitabh.DAO.EventDAO;
import com.example.hisabhkitabh.DAO.EventParticipantsDAO;
import com.example.hisabhkitabh.DAO.UserDAO;
import com.example.hisabhkitabh.Model.Event;
import com.example.hisabhkitabh.Model.EventParticipants;
import com.example.hisabhkitabh.Model.User;
import com.example.hisabhkitabh.R;
import com.example.hisabhkitabh.fragment.DateChooserFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by LNJPC on 19-02-2016.
 */
public class NewTransactionActivity extends AppCompatActivity implements
        android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private static Context mContext;
    private static final int CONTACTS_LIST_LOADER_ID = 0;
    private static final int FILTERED_CONTACTS_LIST_LOADER_ID = 1;

    private static  ArrayList<String> arrayList = new ArrayList<>();
    private User mNewuser ;
    private static User PrimeUser;
    private static Spinner SPINNER ;
    private Handler mHandler;
    private SimpleCursorAdapter mSimpleCursorAdapter ;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = NewTransactionActivity.this;
        setContentView(R.layout.newtransaction_layout);
        SPINNER = (Spinner)findViewById(R.id.spinner);
        mHandler = new Handler(Looper.getMainLooper()){

            @Override
            public void handleMessage(Message msg) {

                if(msg.what == 3){
                    Toast.makeText(NewTransactionActivity.this, "Transaction Added", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(NewTransactionActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
                }
            }
        };


         Toolbar toolbar = (Toolbar) findViewById(R.id.transaction_toolbar);
            setSupportActionBar(toolbar);

         final AutoCompleteTextView searchContactsTextView = (AutoCompleteTextView) findViewById(R.id.searchContactsTextView);
         searchContactsTextView.setThreshold(2);


        searchContactsTextView.setHint("Enter names");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            searchContactsTextView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                @Override
                public void onDismiss() {
                 String name =  ((AutoCompleteTextView) findViewById(R.id.searchContactsTextView)).getText().toString();
                    if (name !=null) {
                        setSecondUser(name);
                    }
                    else{
                        searchContactsTextView.setError("Enter the name");
                    }
                }
            });
        }

        //getting the name of the person with whom our App user transacted

        searchContactsTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String secondUser = searchContactsTextView.getText().toString();
                setSecondUser(secondUser);

            }
        });


        // Getting our App first user info.
        new Thread(new Runnable() {
            @Override
            public void run() {
                 PrimeUser = new UserDAO().getFirstUser(mContext);

            }
        }).start();



        final String [] COLUMNS_PROJECTION = new  String  [] {ContactsContract.Contacts.DISPLAY_NAME} ;



      mSimpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                COLUMNS_PROJECTION,
                new int [] {android.R.id.text1},
                0);


        mSimpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
              String  name =   cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                return name;
            }
        });

          searchContactsTextView.setAdapter(mSimpleCursorAdapter);
          getSupportLoaderManager().initLoader(FILTERED_CONTACTS_LIST_LOADER_ID, null, this);

          searchContactsTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCursor(s);
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });

    }



    public void setSecondUser (String secondUser){

        String [] name =  secondUser.split(" ");
        mNewuser = new User();
        mNewuser.setFirstName(name[0]);
        if(name.length >1){
            mNewuser.setLastName(name[1]);
            setSpinner(secondUser, SPINNER);
        }
        else {
            setSpinner(secondUser,SPINNER);
        }


    }


 public void setSpinner (String secondUser,Spinner spinner){
     if(secondUser !=null){
         arrayList.clear();
         arrayList.add(secondUser+ " Owes You Full");
         arrayList.add("You Owes full");
         ArrayAdapter<String> arrayAdapter =   new ArrayAdapter<String>(mContext,
                 android.R.layout.simple_expandable_list_item_1,
                 android.R.id.text1,
                 arrayList);

         spinner.setAdapter(arrayAdapter);

     }
     else {
         arrayList.clear();
         arrayList.add("You Owes full");
         ArrayAdapter<String> arrayAdapter =   new ArrayAdapter<String>(mContext,
                 android.R.layout.simple_expandable_list_item_1,
                 android.R.id.text1,
                 arrayList);

         spinner.setAdapter(arrayAdapter);

     }

 }

    private void filterCursor(CharSequence query ) {
        Bundle bundle = new Bundle ();
        bundle.putCharSequence("newQuery", query);
        getSupportLoaderManager().restartLoader(CONTACTS_LIST_LOADER_ID, bundle, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newtransaction, menu);
        return true;
    }

    //Contacts rows to retrieve

    static final String [] CONTACTS_SUMMARY_PROJECTION = new String [] {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID
    };

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {


        switch (id) {


            case (FILTERED_CONTACTS_LIST_LOADER_ID) :

                if(args != null) {

                    String query = args.getCharSequence("newQuery").toString();
                    String select = ContactsContract.Contacts.DISPLAY_NAME + " LIKE +'" + query + "%'";

                    return new CursorLoader(this,
                            ContactsContract.Contacts.CONTENT_URI,
                            CONTACTS_SUMMARY_PROJECTION,
                            select,
                            null,
                            ContactsContract.Contacts.DISPLAY_NAME + " ASC");
                }
                else
                return new CursorLoader(this,
                        ContactsContract.Contacts.CONTENT_URI,
                        CONTACTS_SUMMARY_PROJECTION,
                        null,
                        null,
                        ContactsContract.Contacts.DISPLAY_NAME + " ASC");

            default:
                String query_ = args.getCharSequence("newQuery").toString();
                String select_ = ContactsContract.Contacts.DISPLAY_NAME + " LIKE +'" + query_ + "%'";

                return new CursorLoader(this,
                        ContactsContract.Contacts.CONTENT_URI,
                        CONTACTS_SUMMARY_PROJECTION,
                        select_,
                        null,
                        ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        }

    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader loader, Cursor data) {
        mSimpleCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        mSimpleCursorAdapter.swapCursor(null);
    }


    public void showDatePickerDialog(View view) {
        DialogFragment dialogFragment = new DateChooserFragment();
        dialogFragment.show(getFragmentManager(),"R.string.date_fragment_tag");


    }

    public void addTransaction(MenuItem item) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Event event ;
                int flag = 0;
                EventDAO eventDAO = new EventDAO() ;
                String description =  ((EditText)findViewById(R.id.description)).getText().toString();
                SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd MMM,yyyy");

                Date date = null;

                try {

                    date = new Date(simpleDateFormat.parse(((Button)findViewById(R.id.date_fragment)).getText().toString()).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                event = new Event(date,description);

               if(eventDAO.insertEvent(event,mContext)!=-1) {
                   flag++;
               }

                UserDAO userDAO = new UserDAO();
               if(userDAO.insertUser(mNewuser,mContext)!= -1) {
                   flag++;
               }

                EventParticipants eventParticipants ;
                EventParticipantsDAO eventParticipantsDAO = new EventParticipantsDAO();
                double amount =  Double.parseDouble(((EditText)findViewById(R.id.amount)).getText().toString());
                eventParticipants = classfifyEventParticipants(SPINNER.getSelectedItemPosition(),amount);
                if(eventParticipantsDAO.insertEventParticipants(eventParticipants,mContext)!=-1){
                    ++flag;
                }

                Message message = mHandler.obtainMessage(flag);
                message.sendToTarget();

            }
        }).start();
        this.finish();


    }




    public EventParticipants classfifyEventParticipants (int position,double amount ){

        switch (position) {

            case 0:
                return new EventParticipants(PrimeUser,mNewuser,amount);

            case 1:
                return new EventParticipants(mNewuser,PrimeUser,amount);

          default:
              return null;
        }

    }


}
