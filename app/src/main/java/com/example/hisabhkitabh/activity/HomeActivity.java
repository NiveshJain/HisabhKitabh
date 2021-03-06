package com.example.hisabhkitabh.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hisabhkitabh.R;
import com.example.hisabhkitabh.fragment.ContactsListFragment;
import com.example.hisabhkitabh.fragment.GroupListFragment;
import com.example.hisabhkitabh.fragment.ReportFragment;


public class HomeActivity extends AppCompatActivity{


    SharedPreferences prefs = null;
    private android.support.v7.widget.Toolbar toolbar ;
    private static android.app.ActionBar actionBar ;

    private  final int SIGN_IN_REQUEST_CODE = 0;
    private  static String appOwner_name = null;
    private  static String contact_no = null;

    private static final String CUSTOM_ACTION = "com.example.hisabhkitabh.action.ADD_TRANSACTION";

    private static final int NUM_TABS = 3 ;
    private ViewPager mViewPager;
    private FragmentSlidePagerAdapter  mPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         prefs = getSharedPreferences("com.example.hisabhkitabh.newInstallation",MODE_PRIVATE);

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
        tabLayout.addTab(tabLayout.newTab().setText("List"));
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
    protected void onResume() {
        super.onResume();

        if(prefs.getBoolean("com.example.hisabhkitabh.newInstallation",true)){
            prefs.edit().putBoolean("com.example.hisabhkitabh.newInstallation",false).apply();
            startActivityForResult(new Intent().setClass(getApplicationContext(), SignInActivity.class), SIGN_IN_REQUEST_CODE);

        }
    }

    // Set the app User in the Database having user_id as 1
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == SIGN_IN_REQUEST_CODE)
           if(resultCode == RESULT_OK){

           }

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


    public void addNewTransaction(View view) {

        startActivity(new Intent().setAction(CUSTOM_ACTION));

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

   void  refreshTransactionList (View view){
      ContactsListFragment contactsListFragment = new ContactsListFragment();
        ContactsListFragment.Contacts contacts = contactsListFragment.new Contacts();
       contacts.execute(this);

   }


}



