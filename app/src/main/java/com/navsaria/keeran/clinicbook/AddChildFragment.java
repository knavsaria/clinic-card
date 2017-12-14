package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by keeran on 2017/11/24.
 */

public class AddChildFragment extends DialogFragment {

    private EditText mFirstName;
    private EditText mLastName;
    private RadioGroup mIsBoy;
    private TextView mError;
    private TextInputLayout mFirstNameInputLayout;
    private TextInputLayout mLastNameInputLayout;

    public static final String FIRST_NAME =
            "com.navsaria.keeran.clinicbook.firstname";

    public static final String LAST_NAME =
            "com.navsaria.keeran.clinicbook.lastname";

    public static final String GENDER =
            "com.navsaria.keeran.clinicbook.gender";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_child, null);
        mFirstName = (EditText) v.findViewById(R.id.add_first_name);
        mLastName = (EditText) v.findViewById(R.id.add_last_name);
        mIsBoy = (RadioGroup) v.findViewById(R.id.child_gender);
        mError = (TextView) v.findViewById(R.id.adding_child_error_textview);
        mFirstNameInputLayout = (TextInputLayout) v.findViewById(R.id.input_layout_firstName);
        mLastNameInputLayout = (TextInputLayout) v.findViewById(R.id.input_layout_surname);



        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                .setView(v)
                .setTitle(R.string.add_child_title)
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

                        boolean genderError = false;
                        boolean firstNameError = false;
                        boolean lastNameError = false;


                        boolean isValid = true;

                        String firstName = mFirstName.getText().toString();
                        if (mFirstName.getText().length() == 0) {
                            mFirstNameInputLayout.setError(getString(R.string.error_message_adding_child, "first name"));
                            isValid = false;
                        } else {
                            mFirstNameInputLayout.setErrorEnabled(false);
                        }

                        String lastName = mLastName.getText().toString();
                        if (mLastName.getText().length() == 0) {
                            mLastNameInputLayout.setError(getString(R.string.error_message_adding_child, "surname"));
                            isValid = false;
                        } else {
                            mLastNameInputLayout.setErrorEnabled(false);
                        }

                        boolean isBoy = false;
                        int genderSelected = mIsBoy.getCheckedRadioButtonId();
                        if (genderSelected == R.id.child_is_boy) {
                            isBoy = true;
                        } else if (genderSelected == R.id.child_is_girl) {
                            isBoy = false;
                        } else {
                            isValid = false;
                            mError.setText(getString(R.string.error_message_adding_child, "gender"));
                            mError.setVisibility(TextView.VISIBLE);
                        }

                        if (isValid) {
                            sendResult(Activity.RESULT_OK, firstName, lastName, isBoy);
                            alertDialog.dismiss();
                        }
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



    private void sendResult(int resultCode, String firstName, String lastName, boolean isBoy) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(FIRST_NAME, firstName);
        intent.putExtra(LAST_NAME, lastName);
        intent.putExtra(GENDER, isBoy);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
