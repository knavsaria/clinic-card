package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/30.
 */

public class PersonalDataFragment extends Fragment {

    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARGS_ID = "child_id";
    private static final int REQUEST_CODE = 0;
    public static final String INSTANCE_STATE_CHILD = "com.navsaria.keeran.mchild";

    private Button mDatePicker;
    private Child mChild;
    private EditText mChildFirstName;
    private EditText mChildSurname;
    private EditText mChildIdNum;
    private EditText mMotherIdNum;


    public static PersonalDataFragment newInstance(UUID childId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID, childId);

        PersonalDataFragment fragment = new PersonalDataFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mChild = (Child) savedInstanceState.getSerializable(INSTANCE_STATE_CHILD);
        } else {
            UUID childId = (UUID) getArguments().getSerializable(ARGS_ID);
            mChild = ChildList.getChildList(getActivity()).getChild(childId);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(INSTANCE_STATE_CHILD, mChild);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_data, container, false);
        mDatePicker = (Button) v.findViewById(R.id.dob_child_button);
        updateDate();
        mDatePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mChild.getDob());
                dialog.setTargetFragment(PersonalDataFragment.this, 0);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mChildFirstName = (EditText) v.findViewById(R.id.edit_text_child_firstname);
        mChildFirstName.setText(mChild.getFirstName());

        mChildSurname = (EditText) v.findViewById(R.id.edit_text_child_surname);
        mChildSurname.setText(mChild.getLastName());

        mChildIdNum = (EditText) v.findViewById(R.id.edit_text_child_id);


        mMotherIdNum = (EditText) v.findViewById(R.id.edit_text_mother_id);
        return v;
    }

    private void updateDate() {
        mDatePicker.setText(mChild.getDob().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mChild.setDob(date);
            updateDate();
        }
    }
}
