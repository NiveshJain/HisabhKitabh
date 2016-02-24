package com.example.hisabhkitabh.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hisabhkitabh.R;
import com.example.hisabhkitabh.DAO.Contract;
import com.example.hisabhkitabh.DAO.DBHelper;
import com.example.hisabhkitabh.fragment.ContactsListFragment;
import com.example.hisabhkitabh.fragment.GroupListFragment;
import com.example.hisabhkitabh.fragment.ReportFragment;


public class HomeActivity extends AppCompatActivity{


    SharedPreferences prefs = null;
    private android.support.v7.widget.Toolbar toolbar ;
     static android.app.ActionBar actionBar ;

    private  static String appOwner_name = null;
    private  static String contact_no = null;

    private static  int SIGN_IN_REQUEST_CODE = 0;

    private static final int NUM_TABS = 3 ;
    private ViewPager mViewPager;
    private FragmentSlidePagerAdapter  mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar =  (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupActionBar();

         mViewPager = (ViewPager) findViewById(R.id.pager);
         mPagerAdapter = new FragmentSlidePagerAdapter(getSupportFragmentManager());
         mViewPager.setAdapter(mPagerAdapter);
         mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(setTabs()));


       }

    private TabLayout setTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Contancts"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.addTab(tabLayout.newTab().setText("Reports"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return tabLayout;
    }


    private void  setupActionBar (){

        ActionBar actionBar =  getSupportActionBar();
        assert actionBar != null;
        actionBar.setCustomView(R.layout.actionbar_layout);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

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


    public static class FragmentSlidePagerAdapter extends FragmentStatePagerAdapter {

        public FragmentSlidePagerAdapter ( FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:

                    return new ContactsListFragment ();
                case 1:
                   return new GroupListFragment();
                case 2:
                    return new ReportFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_TABS;
        }
    }

    public  class Contacts extends AsyncTask<Activity ,Void, String []> {

        private DBHelper database  ;
        private SQLiteDatabase db;
        private FragmentActivity mactivity;

        public Contacts(FragmentActivity activity){
            mactivity = activity;

        }

        @Override
        protected String [] doInBackground(  Activity...  params) {

            database = new DBHelper(params[0]);

            db = database.getReadableDatabase();


            Cursor cursor = db.rawQuery("select " + Contract.Users.COLUMN_FIRST_NAME + " from " + Contract.Users.TABLE_NAME, null);

            String [] names = new String [cursor.getCount()] ;
            if (cursor.getCount()== 0){
                cursor.close();
                db.close();
                return null;
            }
            else
            {
                int count =  cursor.getCount();

                if(cursor.moveToFirst()) {
                    do {
                        names[cursor.getPosition()] = cursor.getString(cursor.getColumnIndex(Contract.Users.COLUMN_FIRST_NAME));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
            }
            return  names;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            ListView listview =  (ListView) HomeActivity.this.findViewById(R.id.list);

            if(strings != null) {
                ArrayAdapter adapter = new ArrayAdapter<String>(mactivity, R.layout.contact_item_layout, R.id.name, strings);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    }
                });
            }
            else {
                listview.setVisibility(View.INVISIBLE);
            }
        }
    }


}


