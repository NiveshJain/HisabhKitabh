package com.example.hisabhkitabh.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Spinner;

import com.example.hisabhkitabh.DAO.UserDAO;
import com.example.hisabhkitabh.Model.User;
import com.example.hisabhkitabh.R;
import com.example.hisabhkitabh.fragment.DateChooserFragment;

/**
 * Created by LNJPC on 19-02-2016.
 */
public class NewTransactionActivity extends AppCompatActivity implements
        android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private static Context mContext;
    private static final int CONTACTS_LIST_LOADER_ID = 0;
    private static final int FILTERED_CONTACTS_LIST_LOADER_ID = 1;
    private static String name = "";


    SimpleCursorAdapter mSimpleCursorAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.newtransaction_layout);

         Toolbar toolbar = (Toolbar) findViewById(R.id.transaction_toolbar);
        setSupportActionBar(toolbar);

         final AutoCompleteTextView searchContactsTextView = (AutoCompleteTextView) findViewById(R.id.searchContactsTextView);
         searchContactsTextView.setThreshold(2);


        searchContactsTextView.setHint("Enter names");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            searchContactsTextView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                @Override
                public void onDismiss() {
                  name =  ((AutoCompleteTextView) findViewById(R.id.searchContactsTextView)).getText().toString();
                    if (name !=null) {

                    }
                }
            });
        }

        //getting the name of the person with whom our App user transacted
        final String[] secondUser = new String[] {""};
        searchContactsTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondUser[0] = (String) mSimpleCursorAdapter.getItem(position);
            }
        });

        // Getting our App first user info.
        final User[] firstUser = new User[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                 firstUser[0] = new UserDAO().getFirstUser(mContext);
            }
        }).start();

        //Initializig Spinner

       ArrayAdapter<String> arrayAdapter =   new ArrayAdapter<String>(mContext,
            android.R.layout.simple_expandable_list_item_1,
            android.R.id.text1,
            new String [] {"You Owe",secondUser[0],"Split Equally"});

        ((Spinner)findViewById(R.id.spinner)).setAdapter(arrayAdapter);

        final String [] COLUMNS_PROJECTION = new  String  [] {ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID} ;



      mSimpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,
                COLUMNS_PROJECTION,
                new int [] {android.R.id.text1,android.R.id.text2},
                0);


        mSimpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
                name =   cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
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

    private void filterCursor(CharSequence query ) {
        Bundle bundle = new Bundle ();
        bundle.putCharSequence("newQuery", query);
        getSupportLoaderManager().restartLoader(FILTERED_CONTACTS_LIST_LOADER_ID, bundle, this);
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
                return null;
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



    }
}
