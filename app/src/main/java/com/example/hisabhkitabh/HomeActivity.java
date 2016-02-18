package com.example.hisabhkitabh;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


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
                    ContactsListFragment contactsListFragment = new ContactsListFragment();

                    return contactsListFragment;
                case 1:
                    GroupListFragment groupListFragment = new GroupListFragment();
                    return groupListFragment;
                case 2:
                    ReportFragment reportFragment = new ReportFragment();
                    return reportFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_TABS;
        }
    }


    public static class ContactsListFragment extends Fragment {

        public ContactsListFragment () {}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view =  inflater.inflate(R.layout.contacts_list,container,false);

            return  view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            String [] names = {"Nivesh","Shashank","Ratnesh","Suyash","Sangeet","Yash","Sourabh","Krati","Kapil"};

            ListView listview =  (ListView)  getActivity().findViewById(R.id.list);

            ArrayAdapter adapter = new ArrayAdapter<String> (this.getActivity(),R.layout.contact_item_layout,R.id.name,names);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                }
            });
        }
    }

    public  static  class GroupListFragment extends Fragment {

        public GroupListFragment () {}
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.groupcontactslist_layout,container,false);
        }
    }

    public  static  class ReportFragment extends Fragment {

        public  ReportFragment () {}
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.report_layout,container,false);
        }
    }

   }



