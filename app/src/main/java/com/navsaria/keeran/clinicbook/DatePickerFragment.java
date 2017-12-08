package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by keeran on 2017/11/30.
 */

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.navsaria.keeran.date";
    public static final int CHILD_TITLE = 0;
    public static final int MOTHER_TITLE = 1;
    public static final int FATHER_TITLE = 2;
    public static final int VACCINE_TITLE = 3;


    private DatePicker mDatePicker;
    private String mTitleSubject;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year = 2012;
        int month = 7;
        int day = 9;

        if (getArguments() != null) {
            Date date = (Date) getArguments().getSerializable((ARG_DATE));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        View v = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_date, null);

        switch (getTargetRequestCode()){
            case CHILD_TITLE:
                mTitleSubject = "Child's Date of Birth";
                break;
            case  MOTHER_TITLE:
                mTitleSubject = "Mother's Date of Birth";
                break;
            case FATHER_TITLE:
                mTitleSubject = "Father's Date of Birth";
                break;
            case VACCINE_TITLE:
                mTitleSubject = "Date of Vaccination";
                break;
            default:
                break;
        }

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(mTitleSubject)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year, month, day).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();
    }

    private void sendResult (int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);

    }

}
