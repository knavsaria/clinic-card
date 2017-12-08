package com.navsaria.keeran.clinicbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

/**
 * Created by keeran on 2017/12/08.
 */

public class ImmunisationFragment extends Fragment {

    private static final String ARGS_ID = "child_id";

    public static ImmunisationFragment newInstance(UUID childId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID, childId);

        ImmunisationFragment fragment = new ImmunisationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_immunisation, container, false);

        return v;
    }
}
