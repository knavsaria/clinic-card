package com.navsaria.keeran.clinicbook;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by keeran on 2017/11/24.
 */

public class AddChildFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_child, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_child_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
