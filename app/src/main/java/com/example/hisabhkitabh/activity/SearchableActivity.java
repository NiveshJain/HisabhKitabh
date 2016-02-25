package com.example.hisabhkitabh.activity;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;

import com.example.hisabhkitabh.R;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class SearchableActivity extends ListActivity implements LoaderManager.LoaderCallbacks {

    private static final int LOADER_ID = 0;
    private String searchContact ;
    private SimpleCursorAdapter madapter ;

    public void setQuery(String query) {
        this.searchContact = query;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchContact(query);
        }



    }

    private void searchContact(String query) {

        setQuery(query);
        madapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.searchview_layout,null,
                new String [] {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY},
                new int [] {android.R.layout.simple_list_item_1},0);

        setListAdapter(madapter);
        getLoaderManager().initLoader(LOADER_ID,null, (android.app.LoaderManager.LoaderCallbacks)this);
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        String [] contacts = {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        switch (id){
            case LOADER_ID:
                return new CursorLoader(
                    getApplicationContext(),
                        ContactsContract.Contacts.CONTENT_FILTER_URI,
                        contacts,
                        null,
                        null,
                        null);

            default:
                return null;

        }

    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        madapter.swapCursor( (Cursor)data);

    }

    @Override
    public void onLoaderReset(Loader loader) {

        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        madapter.swapCursor(null);

    }
}
