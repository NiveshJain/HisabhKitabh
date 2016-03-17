package com.example.hisabhkitabh.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.hisabhkitabh.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LNJPC on 17-03-2016.
 */
public class DateChooserFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
       Button button = (Button)getActivity().findViewById(R.id.date_fragment);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
         Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd MMM, yyyy");
        button.setText( simpleDateFormat.format(date));

    }


}
