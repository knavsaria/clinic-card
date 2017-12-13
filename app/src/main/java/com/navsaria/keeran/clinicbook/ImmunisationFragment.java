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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by keeran on 2017/12/08.
 */

public class ImmunisationFragment extends Fragment {

    private static final String ARGS_ID = "child_id";

    private final int ADD_VACCINE = 0;

    private final String ADD_VACCINE_TAG = "com.navsaria.keeran.child.addVaccine";



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
                addVaccineFragment .setTargetFragment(ImmunisationFragment.this, ADD_VACCINE);
                addVaccineFragment.show(fm, ADD_VACCINE_TAG);
            }
        });


        return v;
    }

    private void getChildsListOfVaccines() {
        List<String> test = mChild.getVaccines();
        if (mChild.getVaccines().size() > 0) {
            mListOfVaccines = mVaccineList.getVaccines(mChild.getVaccines());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == ADD_VACCINE) {
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
        }
    }

    private void updateView() {

            if (mAdapter == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                mAdapter = new VaccineAdapter(getActivity(), mListOfVaccines, mChild);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setVaccineList(mListOfVaccines);
                mAdapter.notifyDataSetChanged();
            }
    }


/*    public class VaccineHolder extends RecyclerView.ViewHolder {

        public VaccineHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.vaccine_list_item, parent, false));
        }

        public void bind(Vaccine vaccine) {
            TextView ageGroup = (TextView) itemView.findViewById(R.id.text_view_age_group);
            ageGroup.setText(getString(R.string.label_age_group, vaccine.getAgeGroup()));

            TextView batchNumber = (TextView) itemView.findViewById(R.id.text_view_batch_number);
            batchNumber.setText(getString(R.string.label_batch_number, vaccine.getBatchNumber()));

*//*            TextView mSite = (TextView) itemView.findViewById(R.id.text_view_site);
            mSite.setText(getString(R.string.label_site, vaccine.getSite()));*//*

            TextView vaccineCode = (TextView) itemView.findViewById(R.id.text_view_vaccine_code);
            vaccineCode.setText(getString(R.string.label_vaccine_code, vaccine.getVaccineCode()));

            TextView date = (TextView) itemView.findViewById(R.id.text_view_vaccine_date);
            date.setText(getString(R.string.label_vaccine_date, vaccine.getDateGiven().toString()));

            ImageView deleteVaccine = (ImageView) itemView.findViewById(R.id.img_delete_vaccine);
            deleteVaccine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Vaccine Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }// End of ViewHolder*/



/*    public class VaccineAdapter extends RecyclerView.Adapter<VaccineHolder> {

        private List<Vaccine> mVaccines;


        public VaccineAdapter (List<Vaccine> vaccines) {
            mVaccines = vaccines;
        }

        @Override
        public VaccineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VaccineHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(VaccineHolder holder, int position) {
            holder.bind(mVaccines.get(position));
        }

        @Override
        public int getItemCount() {
            return mVaccines.size();
        }

        public void setVaccineList(List<Vaccine> vaccines) {
            mVaccines = vaccines;
        }
    } // End of Adapter*/


}// End of class
