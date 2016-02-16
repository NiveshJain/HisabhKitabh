package com.example.hisabhkitabh;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends AppCompatActivity{


    SharedPreferences prefs = null;
    private android.support.v7.widget.Toolbar toolbar ;
     static android.app.ActionBar actionBar ;

    private  static String appOwner_name = null;
    private  static String contact_no = null;

    private static  int SIGN_IN_REQUEST_CODE = 0;

    private static final int NUM_PAGES  = 3 ;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar =  (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mPagerAdapter = new FragmentSlidePagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mPagerAdapter);
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

    public static class FragmentSlidePagerAdapter extends FragmentPagerAdapter {

        public FragmentSlidePagerAdapter ( FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new ContactsListFragment();
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

   }



