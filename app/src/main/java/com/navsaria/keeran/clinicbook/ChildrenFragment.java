package com.navsaria.keeran.clinicbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/22.
 */

public class ChildrenFragment extends Fragment {

    private ChildList mChildList;
    private FloatingActionButton mAddChild;
    private LinearLayout mLinearLayout;

    private static final String ADD_CHILD_DIALOG = "AddChildDialog";
    private static final int CHILD_DETAILS = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_children_list, container, false);
        mLinearLayout= (LinearLayout) v.findViewById(R.id.linear_layout_children);

        //Fetch children from mChildList and update UI
        updateUI();

        //Add the AddChild button to UI
        mAddChild = (FloatingActionButton) v.findViewById(R.id.add_child);
        mAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                AddChildFragment addChildDialog = new AddChildFragment();
                addChildDialog.setTargetFragment(ChildrenFragment.this, CHILD_DETAILS);
                addChildDialog.show(fragmentManager, ADD_CHILD_DIALOG);
            }
        });

/*        Button testButton = (Button) v.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vaccine vaccineTest = new Vaccine();
                vaccineTest.setDateGiven(new Date());
                vaccineTest.setBatchNumber("M5465");
                vaccineTest.setAgeGroup(14);
                vaccineTest.setSite("left arm");
                vaccineTest.setVaccineCode("BCG");

                VaccineList vaccineList = VaccineList.getVaccineList(getActivity());
                vaccineList.addVaccine(vaccineTest);

                Vaccine vaccineResult = vaccineList.getVaccine(vaccineTest.getId());
                int i = 0;
            }
        });*/

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        ParentList mParentList = ParentList.getParentList(getActivity());
        if (requestCode == CHILD_DETAILS) {
            Child child = new Child();
            String firstName = data.getStringExtra(AddChildFragment.FIRST_NAME);
            String lastName = data.getStringExtra(AddChildFragment.LAST_NAME);
            boolean isBoy = data.getBooleanExtra(AddChildFragment.GENDER, false);
            child.setFirstName(firstName);
            child.setLastName(lastName);
            child.setBoy(isBoy);
            Parent father = new Parent();
            father.setADad(true);
            Parent mother = new Parent();
            mother.setADad(false);
            child.setFather(father.getId());
            child.setMother(mother.getId());
            mChildList.addChild(child);
            //Add father and mother to parent database
            mParentList.addParent(father);
            mParentList.addParent(mother);
            updateUI();
        }
    }

    private void updateUI() {
        mChildList = ChildList.getChildList(getActivity()); // get all children stored in ChildList

        int index = 0;
        View childView;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mLinearLayout.removeAllViews();
        for (final Child child : mChildList.getChildren()) {
            childView = inflater.inflate(R.layout.child_view, mLinearLayout, false);
            TextView childNameView = (TextView) childView.findViewById(R.id.child_name);
            String fullName = getString(R.string.child_full_name, child.getFirstName(), child.getLastName());
            childNameView.setText(fullName);
            childNameView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    //Toast.makeText(getActivity(), firstName + " " + lastName, Toast.LENGTH_SHORT).show();
                    Intent intent = ChildDetailsActivity.newIntent(getActivity(), child.getId());
                    startActivity(intent);
                }
            });

            ImageView deleteChild = (ImageView) childView.findViewById(R.id.img_delete_child);
            deleteChild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteChild(child);
                }
            });
            mLinearLayout.addView(childView, index);
            index++;
        }
    }

    private void deleteChild(Child child) {
        VaccineList vaccineList = VaccineList.getVaccineList(getActivity());
        ChildList childList = ChildList.getChildList(getActivity());
        for (String vaccineId: child.getVaccines()) {
            vaccineList.deleteVaccine(UUID.fromString(vaccineId));
        }
        childList.deleteChild(child.getId());
        updateUI();
    }

}
