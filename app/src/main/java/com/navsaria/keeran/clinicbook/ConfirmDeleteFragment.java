package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by keeran on 2017/12/13.
 */

public class ConfirmDeleteFragment extends DialogFragment {

    private Child mChild;


    private static final String ARGS_CHILD = "com.navsaria.keeran.childArgs";
    public static final String EXTRA_CHILD = "com.navsaria.keeran.childExtra";


    public static ConfirmDeleteFragment newInstance(Child child) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_CHILD, child);
        ConfirmDeleteFragment confirmDeleteFragment = new ConfirmDeleteFragment();
        confirmDeleteFragment.setArguments(args);
        return confirmDeleteFragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.confirm_delete, null);

        if (getArguments() != null) {
            mChild = (Child) getArguments().getSerializable(ARGS_CHILD);
        }

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.label_delete_dialog)
                .setPositiveButton(android.R.string.yes, null)
                .setNegativeButton(android.R.string.no, null)
                .create();


        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        if (mChild != null) {
                            intent.putExtra(EXTRA_CHILD, mChild);
                        }
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
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
}
