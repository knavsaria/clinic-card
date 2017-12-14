package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by keeran on 2017/12/08.
 */

public class ImmunisationFragment extends Fragment implements VaccineAdapter.OnItemClickListener {

    private static final String ARGS_ID = "child_id";

    private final int ADD_VACCINE_REQUEST_CODE = 0;
    private final int DELETE_VACCINE_REQUEST_CODE = 1;

    private final String ADD_VACCINE_TAG = "com.navsaria.keeran.child.addVaccine";
    private final String DELETE_VACCINE_TAG = "com.navsaria.keeran.child.deleteVaccine";




    private int mPositionToDelete;
    private Vaccine mVaccineToDelete;
    private RecyclerView mRecyclerView;
    private VaccineAdapter mAdapter;
    private VaccineList mVaccineList;
    private List<Vaccine> mListOfVaccines;
    private Child mChild;

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

        mListOfVaccines = new ArrayList<>();
        mChild = ChildList.getChildList(getActivity()).getChild((UUID) getArguments().getSerializable(ARGS_ID));
        mVaccineList = VaccineList.getVaccineList(getActivity());
        getChildsListOfVaccines();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.vaccine_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        updateView();

        FloatingActionButton addVaccineButton = (FloatingActionButton) v.findViewById(R.id.add_vaccine_button);
        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AddVaccineFragment addVaccineFragment = new AddVaccineFragment();
                addVaccineFragment .setTargetFragment(ImmunisationFragment.this, ADD_VACCINE_REQUEST_CODE);
                addVaccineFragment.show(fm, ADD_VACCINE_TAG);
            }
        });


        return v;
    }

    private void getChildsListOfVaccines() {
        if (mChild.getVaccines().size() > 0) {
            mListOfVaccines = mVaccineList.getVaccines(mChild.getVaccines());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == ADD_VACCINE_REQUEST_CODE) {
            int ageGroup = data.getIntExtra(AddVaccineFragment.AGE_GROUP, 0);
            String vaccineCode = data.getStringExtra(AddVaccineFragment.VACCINE_CODE);
            String batchNumber = data.getStringExtra(AddVaccineFragment.BATCH_NUMBER);
            Date vaccineDate = (Date) data.getSerializableExtra(AddVaccineFragment.VACCINE_DATE);
            Vaccine newVaccine = new Vaccine();
            //add new vaccine to child received in arguments of fragment
            mChild.addVaccine(newVaccine.getId());
            //then get the ChildList and update the child with this one
            ChildList.getChildList(getActivity()).updateChild(mChild);

            newVaccine.setAgeGroup(ageGroup);
            newVaccine.setVaccineCode(vaccineCode);
            newVaccine.setBatchNumber(batchNumber);
            newVaccine.setDateGiven(vaccineDate);
            mVaccineList.addVaccine(newVaccine);
            getChildsListOfVaccines();
            updateView();
        } else if (requestCode == DELETE_VACCINE_REQUEST_CODE) {
            deleteVaccine(mVaccineToDelete, mPositionToDelete);
        }
    }

    public void deleteVaccine(Vaccine vaccine, int position) {
        VaccineList vaccineList = VaccineList.getVaccineList(getActivity());
        ChildList childList = ChildList.getChildList(getActivity());
        vaccineList.deleteVaccine(vaccine.getId());
        mChild.removeVaccine(vaccine.getId());
        childList.updateChild(mChild);
        mListOfVaccines = vaccineList.getVaccines(mChild.getVaccines());
        mAdapter.setVaccineList(mListOfVaccines);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, mListOfVaccines.size());
    }

    @Override
    public void onItemClicked(View v, Vaccine vaccine, int position) {
        mPositionToDelete = position;
        mVaccineToDelete = vaccine;
        FragmentManager fm = getFragmentManager();
        ConfirmDeleteFragment confirmDeleteFragment = new ConfirmDeleteFragment();
        confirmDeleteFragment.setTargetFragment(ImmunisationFragment.this, DELETE_VACCINE_REQUEST_CODE);
        confirmDeleteFragment.show(fm, DELETE_VACCINE_TAG);
    }

    private void updateView() {

            if (mAdapter == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                mAdapter = new VaccineAdapter(this, getActivity(), mListOfVaccines, mChild, getFragmentManager());
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setVaccineList(mListOfVaccines);
                mAdapter.notifyDataSetChanged();
            }
    }

}// End of class
