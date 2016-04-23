package com.example.hisabhkitabh.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hisabhkitabh.DAO.UserDAO;
import com.example.hisabhkitabh.Model.User;
import com.example.hisabhkitabh.R;

import java.util.ArrayList;


/**
 * Created by LNJPC on 23-02-2016.
 */
public class ContactsListFragment extends ListFragment {

    private UserDAO mUsersDAO;
    private Activity mActivity;


    public ContactsListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.contacts_list, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Contacts().execute(getActivity());
    }



    public class Contacts extends AsyncTask<Activity, Void, ArrayList<User>> {


        @Override

        protected ArrayList<User> doInBackground(Activity... params) {

            if (params[0] == null) {
                Log.e("NULL**", "null arraylist");
                return null;
            } else {
                mUsersDAO = new UserDAO();
                return mUsersDAO.getAllUsers(params[0]);
            }

        }

        @Override
        protected void onPostExecute(ArrayList<User> users) {
            super.onPostExecute(users);

            if (!users.isEmpty()) {
               User [] userArray = users.toArray( new User [users.size()]);
                String [] names = new String [userArray.length] ;
                for (int i =0;i<userArray.length;i++){
                    if(userArray[i].getLastName()!=null){
                        names[i] = userArray[i].getFirstName()+" "+userArray[i].getLastName();
                    }
                    else{
                        names[i] = userArray[i].getFirstName();
                    }

                }
                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.contact_item_layout, R.id.name,names);
                setListAdapter(arrayAdapter);
            } else {

            }
        }
    }


}


