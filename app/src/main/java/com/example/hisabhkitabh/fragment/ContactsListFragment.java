package com.example.hisabhkitabh.fragment;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.hisabhkitabh.DAO.EventParticipantsDAO;
import com.example.hisabhkitabh.Model.EventParticipants;
import com.example.hisabhkitabh.R;

import java.util.ArrayList;


/**
 * Created by LNJPC on 23-02-2016.
 */
public class ContactsListFragment extends ListFragment {

    private EventParticipantsDAO mEventParticipatsDAO;
    private Context mContext;

    public Context getContext (){
        return mContext;
    }

    public ContactsListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        new Contacts().execute(getActivity());
        return inflater.inflate(R.layout.contacts_list, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context ;
    }

    public  class Contacts extends AsyncTask<Activity, Void, ArrayList<EventParticipants>> {


        @Override

        protected ArrayList<EventParticipants> doInBackground(Activity... params) {

            if (params[0] == null) {
                Log.e("NULL**", "null arraylist");
                return null;
            } else {
               mEventParticipatsDAO = new EventParticipantsDAO();
                return mEventParticipatsDAO.getAllTransactions(params[0]);
            }

        }

        @Override
        protected void onPostExecute(ArrayList<EventParticipants> eventParticipantsList) {

            if (!eventParticipantsList.isEmpty()) {
                TransactionArrayAdapter transactionArrayAdapter = new TransactionArrayAdapter(eventParticipantsList);
                setListAdapter(transactionArrayAdapter);
            }
        }
    }


    public   class TransactionArrayAdapter implements ListAdapter {

        private  ArrayList<EventParticipants> mEventParticipantsList ;

        public TransactionArrayAdapter(ArrayList<EventParticipants> arrayList) {

            mEventParticipantsList = arrayList;
        }

        public ArrayList<EventParticipants> getEventParticipantsList (){
            return mEventParticipantsList;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {


        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return mEventParticipantsList.size();
        }


        @Override
        public Object getItem(int position) {
            return mEventParticipantsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           if(convertView == null){
               LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              convertView =  layoutInflater.inflate(R.layout.contact_item_layout,parent,false);

           }


            ((TextView)convertView.findViewById(R.id.borrower)).setText(mEventParticipantsList.get(position).getBorrower().getFirstName());
            ((TextView)convertView.findViewById(R.id.lender)).setText(mEventParticipantsList.get(position).getLender().getFirstName());
            ((TextView)convertView.findViewById(R.id.money)).setText(String.valueOf(mEventParticipantsList.get(position).getAmount()));

            return  convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

}


