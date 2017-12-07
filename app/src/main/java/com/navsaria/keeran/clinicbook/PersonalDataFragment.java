package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/30.
 */

public class PersonalDataFragment extends Fragment implements View.OnClickListener{

    private static final String ARGS_ID = "child_id";
    public static final String INSTANCE_STATE_CHILD = "com.navsaria.keeran.mchild";
    public static final String INSTANCE_STATE_MOTHER = "com.navsaria.keeran.mMother";

    private final String DATE_PICKER_TAG = "com.navsaria.keeran.child.dataPicker";


    private Child mChild;
    private Parent mMother;
    private ChildList mChildList;
    private ParentList mParentList;

    //Child Info CardView
    private Button mDatePicker;
    private EditText mChildFirstName;
    private EditText mChildSurname;
    private EditText mChildId;
    private EditText mBirthFacility;
    private EditText mChildStayingWith;
    private EditText mAddress;
    private Button mSaveChildButton;
    //Child Info CardView


    //Mother Info CardView
    private Button mMotherDatePicker;
    private EditText mMotherFirstName;
    private EditText mMotherSurname;
    private EditText mMotherIdNumber;
    private EditText mMotherNoOfBirths;
    private EditText mMotherNoAlive;
    //Mother Info CardView




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

        mParentList = ParentList.getParentList(getActivity());

        if (savedInstanceState != null) {
            mChild = (Child) savedInstanceState.getSerializable(INSTANCE_STATE_CHILD);
            mMother = (Parent) savedInstanceState.getSerializable(INSTANCE_STATE_MOTHER);
        } else {
            UUID childId = (UUID) getArguments().getSerializable(ARGS_ID);
            mChild = ChildList.getChildList(getActivity()).getChild(childId);
            mMother = mParentList.getParent(mChild.getMother());
        }
        mChildList = ChildList.getChildList(getActivity());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(INSTANCE_STATE_CHILD, mChild);
        outState.putSerializable(INSTANCE_STATE_MOTHER, mMother);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_data, container, false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mChild.getFirstName() + " " + mChild.getLastName());

        ///// Child CardView
        mDatePicker = (Button) v.findViewById(R.id.dob_child_button);
        mDatePicker.setOnClickListener(this);

        mChildFirstName = (EditText) v.findViewById(R.id.edit_text_child_firstname);
        mChildFirstName.setText(mChild.getFirstName());
        mChildFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mChild.setFirstName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mChildSurname = (EditText) v.findViewById(R.id.edit_text_child_surname);
        mChildSurname.setText(mChild.getLastName());
        mChildSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mChild.setLastName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mChildId = (EditText) v.findViewById(R.id.edit_text_child_id);
        mChildId.setText(mChild.getIdNumber());
        mChildId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mChild.setIdNumber(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBirthFacility = (EditText) v.findViewById(R.id.edit_text_facility);
        mBirthFacility.setText(mChild.getBirthFacility());
        mBirthFacility.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mChild.setBirthFacility(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mChildStayingWith = (EditText) v.findViewById(R.id.edit_text_child_stay_with);
        mChildStayingWith.setText(mChild.getChildStayingWith());
        mChildStayingWith.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mChild.setChildStayingWith(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAddress = (EditText) v.findViewById(R.id.edit_text_child_address);
        mAddress.setText(mChild.getAddress());
        mAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mChild.setAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ///// Child CardView


        ///// Mother CardView
        mMotherDatePicker = (Button) v.findViewById(R.id.dob_mother_button);
        mMotherDatePicker.setText(mMother.getDob().toString());
        mMotherDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mMother.getDob());
                datePickerFragment.setTargetFragment(PersonalDataFragment.this, DatePickerFragment.MOTHER_TITLE);
                datePickerFragment.show(fm, DATE_PICKER_TAG);
            }
        });

        mMotherFirstName = (EditText) v.findViewById(R.id.edit_text_mother_firstname);
        mMotherFirstName.setText(mMother.getFirstName());
        mMotherFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMother.setFirstName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mMotherSurname = (EditText) v.findViewById(R.id.edit_text_mother_surname);
        mMotherSurname.setText(mMother.getLastName());
        mMotherSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMother.setLastName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mMotherIdNumber = (EditText) v.findViewById(R.id.edit_text_mother_id_number);
        mMotherIdNumber.setText(mMother.getIdNumber());
        mMotherIdNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMother.setIdNumber(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ///// Mother CardView

        mSaveChildButton = (Button) v.findViewById(R.id.child_info_save_button);
        mSaveChildButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                mChildList.updateChild(mChild);
                mParentList.updateParent(mMother);
                Toast.makeText(getActivity(), getString(R.string.toast_save_successful), Toast.LENGTH_LONG).show();
            }

        });

        updateView();

        return v;
    }


    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mChild.getDob());
        datePickerFragment.setTargetFragment(PersonalDataFragment.this, DatePickerFragment.CHILD_TITLE);
        datePickerFragment.show(fm, DATE_PICKER_TAG);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("PersonalDataFragment", "onActivityResult Called");
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == DatePickerFragment.CHILD_TITLE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mChild.setDob(date);
            updateView();
        } else if (requestCode == DatePickerFragment.MOTHER_TITLE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMother.setDob(date);
            mMotherDatePicker.setText(mMother.getDob().toString());
        }
    }

    private void updateView() {
        mDatePicker.setText(mChild.getDob().toString());
    }


}
