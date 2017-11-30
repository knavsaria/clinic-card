package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/28.
 */

public class ChildDetailsFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private UUID mChildId;

    private static final String ARGS_ID = "child_id";


    public static ChildDetailsFragment newInstance(UUID childId) {
        ChildDetailsFragment childDetailsFragment = new ChildDetailsFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID, childId);

        childDetailsFragment.setArguments(args);
        return childDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildId = (UUID) getArguments().getSerializable(ARGS_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_details, container, false);

        ChildList childList = ChildList.getChildList(getActivity());
        Child child = childList.getChild(mChildId);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(child.getFirstName() + " " + child.getLastName());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.child_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new CategoryAdapter());

        return  v;
    }

    ///////////////////////////////////////////////////////////////
    //RecyclerView ViewHolder

    private class CategoryHolder extends RecyclerView.ViewHolder {

        public CategoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.category_list_item, parent, false));
        }

        public void bind(String category) {
            TextView categoryTitle = itemView.findViewById(R.id.category_title);
            categoryTitle.setText(category);
        }

    }

    //RecyclerView ViewHolder
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
    //RecyclerView Adapter

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

        private final String[] mCategoryList = {
                "Personal Data",
                "Growth Measurements",
                "Growth Stages",
                "Immunisations",
                "Medications",
                "Allergies"
        };

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new CategoryHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {
            String category = mCategoryList[position];
            holder.bind(category);
        }

        @Override
        public int getItemCount() {
            return mCategoryList.length;
        }
    }
    //RecyclerView Adapter
    ///////////////////////////////////////////////////////////////

}// End of Class ChildDetailsFragment
