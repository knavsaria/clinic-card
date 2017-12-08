package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/28.
 */

public class ChildDetailsFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Child mChild;

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
        UUID childId = (UUID) getArguments().getSerializable(ARGS_ID);
        mChild = ChildList.getChildList(getActivity()).getChild(childId);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_details, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mChild.getFirstName() + " " + mChild.getLastName());

        Button personalDataButton = (Button) v.findViewById(R.id.button_personal_data);
        personalDataButton.setOnClickListener(this);

        Button immunisationsButton = (Button) v.findViewById(R.id.button_immunisations);
        immunisationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ImmunisationActivity.newIntent(getActivity(), mChild.getId());
                startActivity(intent);
            }
        });
        return  v;
    }

    @Override
    public void onClick(View view) {
        Intent intent = PersonalDataActivity.newIntent(getActivity(), mChild.getId());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ChildDetailsFragment", "onDestroy Called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("ChildDetailsFragment", "onStop Called");
    }

}// End of Class ChildDetailsFragment
