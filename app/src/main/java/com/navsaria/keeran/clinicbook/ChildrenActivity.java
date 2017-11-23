package com.navsaria.keeran.clinicbook;


import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;

public class ChildrenActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ChildrenListFragment();
    }

}
