package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/30.
 */

public class PersonalDataFragment extends Fragment {

    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARGS_ID = "child_id";
    public static final String INSTANCE_STATE_CHILD = "com.navsaria.keeran.mchild";

    private RecyclerView mRecyclerView;
    private Child mChild;
    private final LinkedHashMap mLinkedHashMap = new LinkedHashMap();




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
        mLinkedHashMap.put(0, R.layout.personal_data_child_info);
        mLinkedHashMap.put(1, R.layout.personal_data_mother_info);

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
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mChild.getFirstName() + " " + mChild.getLastName());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.personal_data_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new PersonalDataAdapter());

        return v;
    }

    ///////////////////////////////////////////////////////////////
    //PersonalDataAdapter Adapter
    private class PersonalDataAdapter extends RecyclerView.Adapter<PersonalDataChildHolder> {


        @Override
        public PersonalDataChildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            if (viewType == R.layout.personal_data_child_info) {
                return new PersonalDataChildHolder(inflater, parent, viewType);
            } else {
                return null;
            }
        }

        @Override
        public void onBindViewHolder(PersonalDataChildHolder holder, int position) {
            if (position == 0) {
                holder.bind();
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return (Integer) mLinkedHashMap.get(position);
        }
    }
    //PersonalDataAdapter Adapter
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
    //PersonalDataHolder ViewHolder
/*    private abstract class PersonalDataHolder extends RecyclerView.ViewHolder {

        public PersonalDataHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            super(inflater.inflate(layoutId, parent, false));
        }

    }*/
    //PersonalDataHolder ViewHolder
    ///////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////
    //PersonalDataChildHolder ViewHolder

    private class PersonalDataChildHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        private final String DATE_PICKER_TAG = "com.navsaria.keeran.child.dataPicker";

        private Button mDatePicker;

        public PersonalDataChildHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            super(inflater.inflate(layoutId, parent, false));
            mDatePicker = (Button) itemView.findViewById(R.id.dob_child_button);
            updateDate();
            mDatePicker.setOnClickListener(this);
        }

        public void bind() {
            updateDate();

        }

        private void updateDate() {
            mDatePicker.setText(mChild.getDob().toString());
        }

        @Override
        public void onClick(View view) {
            FragmentManager fm = getFragmentManager();
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mChild.getDob());
            datePickerFragment.setTargetFragment(PersonalDataFragment.this, DatePickerFragment.CHILD_TITLE);
            datePickerFragment.show(fm, DATE_PICKER_TAG);
        }
    }
    //PersonalDataChildHolder ViewHolder
    ///////////////////////////////////////////////////////////////
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("PersonalDataFragment", "onActivityResult Called");
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == DatePickerFragment.CHILD_TITLE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mChild.setDob(date);
            mRecyclerView.getAdapter().notifyItemChanged(0);
        }
    }


}
