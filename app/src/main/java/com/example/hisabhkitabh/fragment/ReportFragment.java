package com.example.hisabhkitabh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hisabhkitabh.R;

/**
 * Created by LNJPC on 23-02-2016.
 */
public class ReportFragment extends Fragment {

    public  ReportFragment () {}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.report_layout,container,false);
    }
}