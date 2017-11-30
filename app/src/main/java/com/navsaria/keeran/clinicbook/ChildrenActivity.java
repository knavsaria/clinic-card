package com.navsaria.keeran.clinicbook;


import android.support.v4.app.Fragment;

public class ChildrenActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ChildrenFragment();
    }

}
