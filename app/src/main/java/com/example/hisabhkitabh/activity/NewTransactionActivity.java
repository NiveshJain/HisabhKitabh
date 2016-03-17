package com.example.hisabhkitabh.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.AutoCompleteTextView;

import com.example.hisabhkitabh.R;

/**
 * Created by LNJPC on 19-02-2016.
 */
public class NewTransactionActivity extends AppCompatActivity implements
        android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CONTACTS_LIST_LOADER_ID = 0;
    private static final int FILTERED_CONTACTS_LIST_LOADER_ID = 1;

    SimpleCursorAdapter mSimpleCursorAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtransaction_layout);

         Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.transaction_toolbar);
        setSupportActionBar(toolbar);

         AutoCompleteTextView searchContactsTextView = (AutoCompleteTextView) findViewById(R.id.searchContactsTextView);
         searchContactsTextView.setThreshold(2);

        searchContactsTextView.setHint("Enter names");
        final String [] COLUMNS_PROJECTION = new  String  [] {ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID} ;

         searchContactsTextView.getOnItemClickListener();

      mSimpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                COLUMNS_PROJECTION,
                new int [] {android.R.id.text1},
                0);

        mSimpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
               String name =   cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                return name;
            }
        });

          searchContactsTextView.setAdapter(mSimpleCursorAdapter);
          getSupportLoaderManager().initLoader(CONTACTS_LIST_LOADER_ID, null, this);

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
                String query = args.getCharSequence("newQuery").toString();
                String select =  ContactsContract.Contacts.DISPLAY_NAME + " LIKE +'" + query +"%'";
                return new CursorLoader(this,
                        ContactsContract.Contacts.CONTENT_URI,
                        CONTACTS_SUMMARY_PROJECTION,
                        select,
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


}
