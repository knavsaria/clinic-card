package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Console;

/**
 * Created by keeran on 2017/11/22.
 */

public class ChildrenListFragment extends Fragment {

    private ChildList mChildList;
    private ImageButton mAddChild;
    private LinearLayout mLinearLayout;

    private static final String ADD_CHILD_DIALOG = "AddChildDialog";
    private static final int CHILD_DETAILS = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildList = ChildList.getChildList(getActivity()); // get all children stored in ChildList
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_children_list, container, false);
        mLinearLayout= (LinearLayout) v.findViewById(R.id.linear_layout_children);

        //Fetch children from mChildList and update UI
        updateUI();

        //Add the AddChild button to UI
        mAddChild = v.findViewById(R.id.add_child);
        mAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                AddChildFragment addChildDialog = new AddChildFragment();
                addChildDialog.setTargetFragment(ChildrenListFragment.this, CHILD_DETAILS);
                addChildDialog.show(fragmentManager, ADD_CHILD_DIALOG);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == CHILD_DETAILS) {
            Child child = new Child();
            String firstName = data.getStringExtra(AddChildFragment.FIRST_NAME);
            String lastName = data.getStringExtra(AddChildFragment.LAST_NAME);
            boolean isBoy = data.getBooleanExtra(AddChildFragment.GENDER, false);
            child.setFirstName(firstName);
            child.setLastName(lastName);
            child.setBoy(isBoy);
            mChildList.addChild(child);
            updateUI();
        }
    }

    private void updateUI() {

        int index = 0;
        View childView;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mLinearLayout.removeAllViews();
        for (Child child : mChildList.getChildren()) {
            childView = inflater.inflate(R.layout.child_view, mLinearLayout, false);
            TextView childNameView = (TextView) childView.findViewById(R.id.child_name);
            String fullName = getString(R.string.child_full_name, child.getFirstName(), child.getLastName());
            childNameView.setText(fullName);
            final String firstName = child.getFirstName();
            final String lastName = child.getLastName();
            childView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Toast.makeText(getActivity(), firstName + " " + lastName, Toast.LENGTH_SHORT).show();
                }
            });
            mLinearLayout.addView(childView, index);
            index++;
        }
    }
}
