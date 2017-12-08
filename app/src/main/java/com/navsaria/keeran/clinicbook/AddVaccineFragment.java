package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by keeran on 2017/12/08.
 */

public class AddVaccineFragment extends DialogFragment {

    private Spinner mAgeGroup;
    private EditText mBatchNumber;
    private Spinner mVaccineCode;
    private Button mVaccineDate;

    private int mAgeGroupValue;
    private String mBatchNumberValue;
    private String mVaccineCodeValue;
    private Date mVaccineDateValue;

    public static final String AGE_GROUP =
            "com.navsaria.keeran.clinicbook.ageGroup";

    public static final String BATCH_NUMBER =
            "com.navsaria.keeran.clinicbook.batchumber";

    public static final String VACCINE_CODE =
            "com.navsaria.keeran.clinicbook.vaccineCode";

    public static final String VACCINE_DATE =
            "com.navsaria.keeran.clinicbook.vaccineDate";

    private final String DATE_PICKER_TAG = "com.navsaria.keeran.child.dataPicker";






    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_vaccine, null);

        mAgeGroup = (Spinner) v.findViewById(R.id.spinner_age_group);
        ArrayAdapter<CharSequence> adapterAliveAgeGroup = ArrayAdapter.createFromResource(getActivity(),
                R.array.age_groups, android.R.layout.simple_spinner_item);
        adapterAliveAgeGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAgeGroup.setAdapter(adapterAliveAgeGroup);
        mAgeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mAgeGroupValue = Integer.valueOf((String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mBatchNumber = (EditText) v.findViewById(R.id.edit_text_batch_number);
        mBatchNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mBatchNumberValue = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mVaccineCode = (Spinner) v.findViewById(R.id.spinner_vaccine_code);
        ArrayAdapter<CharSequence> adapterAliveVaccineCode = ArrayAdapter.createFromResource(getActivity(),
                R.array.vaccine_codes, android.R.layout.simple_spinner_item);
        adapterAliveVaccineCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mVaccineCode.setAdapter(adapterAliveVaccineCode);
        mVaccineCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mVaccineCodeValue = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mVaccineDate= (Button) v.findViewById(R.id.vaccine_date_button);
        mVaccineDate.setText(R.string.label_vaccine_date_button);
        mVaccineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setTargetFragment(AddVaccineFragment.this, DatePickerFragment.VACCINE_TITLE);
                datePickerFragment.show(fm, DATE_PICKER_TAG);

            }
        });


        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_vaccine_title)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();


        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                            sendResult(Activity.RESULT_OK, mAgeGroupValue, mVaccineCodeValue, mBatchNumberValue,
                                    mVaccineDateValue);
                            alertDialog.dismiss();

                    }
                });

                Button buttonNegative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                buttonNegative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        return alertDialog;
    }



    private void sendResult(int resultCode, int ageGroup, String vaccineCode,
                            String batchNumber, Date vaccineDate) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(AGE_GROUP, ageGroup);
        intent.putExtra(VACCINE_CODE, vaccineCode);
        intent.putExtra(BATCH_NUMBER, batchNumber);
        intent.putExtra(VACCINE_DATE, vaccineDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("PersonalDataFragment", "onActivityResult Called");
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == DatePickerFragment.VACCINE_TITLE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mVaccineDateValue = date;
            mVaccineDate.setText(date.toString());
        }
    }

}
