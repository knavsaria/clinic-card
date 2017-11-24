package com.navsaria.keeran.clinicbook;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Console;

/**
 * Created by keeran on 2017/11/22.
 */

public class ChildrenListFragment extends Fragment {

    private ChildList mChildList;
    private Button mAddChild;

    private static final String ADD_CHILD_DIALOG = "AddChildDialog";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildList = ChildList.getChildList(getActivity()); // get all children stored in ChildList
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_children_list, container, false);

        //Add children from mChildList to view
//        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linear_layout_children);
//        int index = 0;
//        for (Child child : mChildList.getChildren()) {
//            View childView = inflater.inflate(R.layout.child_view, null);
            TextView childNameView = (TextView) v.findViewById(R.id.child_name);
//            String firstName = child.getFirstName();
//            String lastName = child.getLastName();
//            String fullName = firstName + " " + lastName;
//            TextView textView = new TextView(getActivity());
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            textView.setLayoutParams(layoutParams);
//            textView.setText(fullName);
//            linearLayout.addView(textView, index);
        Child child = mChildList.getChildren().get(0);
        String firstName = child.getFirstName();
            String lastName = child.getLastName();
            String fullName = firstName + " " + lastName;

            childNameView.setText(fullName);
            //linearLayout.addView(childView, index);
            //index++;
        //}
        //Add children from mChildList to view

        mAddChild = v.findViewById(R.id.add_child);
        mAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                AddChildFragment addChildDialog = new AddChildFragment();
                addChildDialog.show(fragmentManager, ADD_CHILD_DIALOG);
            }
        });

        return v;
    }
}
