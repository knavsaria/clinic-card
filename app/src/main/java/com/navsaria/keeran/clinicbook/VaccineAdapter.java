package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by keeran on 2017/12/13.
 */

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineHolder> {

    private List<Vaccine> mVaccines;
    private Context mContext;
    private Child mChild;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClicked(View v, Vaccine vaccine, int position);
    }

    public VaccineAdapter (OnItemClickListener listener, Context context, List<Vaccine> vaccines, Child child, FragmentManager fm) {
        mVaccines = vaccines;
        mContext = context;
        mChild = child;
        mListener = listener;
    }

    @Override
    public VaccineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VaccineHolder(LayoutInflater.from(mContext), parent);
    }

    @Override
    public void onBindViewHolder(VaccineHolder holder, int position) {
        holder.bind(mVaccines.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mVaccines.size();
    }

    public void setVaccineList(List<Vaccine> vaccines) {
        mVaccines = vaccines;
    }

    // ViewHolder
    public class VaccineHolder extends RecyclerView.ViewHolder {

        public VaccineHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.vaccine_list_item, parent, false));
        }

        public void bind(final Vaccine vaccine, final int position) {

            TextView ageGroup = (TextView) itemView.findViewById(R.id.text_view_age_group);
            ageGroup.setText(mContext.getString(R.string.label_age_group, vaccine.getAgeGroup()));

            TextView batchNumber = (TextView) itemView.findViewById(R.id.text_view_batch_number);
            batchNumber.setText(mContext.getString(R.string.label_batch_number, vaccine.getBatchNumber()));

/*            TextView mSite = (TextView) itemView.findViewById(R.id.text_view_site);
            mSite.setText(getString(R.string.label_site, vaccine.getSite()));*/

            TextView vaccineCode = (TextView) itemView.findViewById(R.id.text_view_vaccine_code);
            vaccineCode.setText(mContext.getString(R.string.label_vaccine_code, vaccine.getVaccineCode()));

            TextView date = (TextView) itemView.findViewById(R.id.text_view_vaccine_date);
            date.setText(mContext.getString(R.string.label_vaccine_date, vaccine.getDateGiven().toString()));

            ImageView deleteVaccineButton = (ImageView) itemView.findViewById(R.id.img_delete_vaccine);
            deleteVaccineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(view, vaccine, position);
                }
            });
        }

    }// End of ViewHolder
/*
    public void deleteVaccine(Vaccine vaccine, int position) {
        VaccineList vaccineList = VaccineList.getVaccineList(mContext);
        ChildList childList = ChildList.getChildList(mContext);
        vaccineList.deleteVaccine(vaccine.getId());
        mChild.removeVaccine(vaccine.getId());
        childList.updateChild(mChild);
        mVaccines = vaccineList.getVaccines(mChild.getVaccines());
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mVaccines.size());
    }*/

} // End of Adapter
