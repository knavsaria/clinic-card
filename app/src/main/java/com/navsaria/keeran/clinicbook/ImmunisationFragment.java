package com.navsaria.keeran.clinicbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by keeran on 2017/12/08.
 */

public class ImmunisationFragment extends Fragment {

    private static final String ARGS_ID = "child_id";

    private RecyclerView mRecyclerView;
    private VaccineAdapter mAdapter;
    private VaccineList mVaccineList;

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

        mVaccineList = VaccineList.getVaccineList(getActivity());
        mRecyclerView = (RecyclerView) v.findViewById(R.id.vaccine_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateView();

        Button addVaccineButton = (Button) v.findViewById(R.id.add_vaccine_button);
        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random generator = new Random();
                int ageGroup = generator.nextInt(15);
                String batchNumber = "M" + generator.nextInt(50);
                String vaccineCode = "BCM" + generator.nextInt(10);
                String site = "Left arm";
                Vaccine vaccine = new Vaccine();
                vaccine.setAgeGroup(ageGroup);
                vaccine.setBatchNumber(batchNumber);
                vaccine.setVaccineCode(vaccineCode);
                vaccine.setSite(site);
                vaccine.setDateGiven(new Date());
                mVaccineList.addVaccine(vaccine);
                updateView();
            }
        });


        return v;
    }

    private void updateView() {

            if (mAdapter == null) {
                mAdapter = new VaccineAdapter(mVaccineList.getVaccines());
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setVaccineList(mVaccineList.getVaccines());
                mAdapter.notifyDataSetChanged();
            }
    }


    public class VaccineHolder extends RecyclerView.ViewHolder {



        public VaccineHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.vaccine_list_item, parent, false));
        }

        public void bind(Vaccine vaccine) {
            TextView mAgeGroup = (TextView) itemView.findViewById(R.id.text_view_age_group);
            mAgeGroup.setText(getString(R.string.label_age_group, vaccine.getAgeGroup()));

            TextView mBatchNumber = (TextView) itemView.findViewById(R.id.text_view_batch_number);
            mBatchNumber.setText(getString(R.string.label_batch_number, vaccine.getBatchNumber()));

            TextView mSite = (TextView) itemView.findViewById(R.id.text_view_site);
            mSite.setText(getString(R.string.label_site, vaccine.getSite()));

            TextView mVaccineCode = (TextView) itemView.findViewById(R.id.text_view_vaccine_code);
            mVaccineCode.setText(getString(R.string.label_vaccine_code, vaccine.getVaccineCode()));

            TextView mDate = (TextView) itemView.findViewById(R.id.text_view_vaccine_date);
            mDate.setText(getString(R.string.label_vaccine_date, vaccine.getDateGiven().toString()));
        }

    }// End of ViewHolder



    public class VaccineAdapter extends RecyclerView.Adapter<VaccineHolder> {

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
    } // End of Adapter


}// End of class
