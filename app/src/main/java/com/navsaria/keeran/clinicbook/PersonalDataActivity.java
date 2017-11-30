package com.navsaria.keeran.clinicbook;

import android.support.v4.app.Fragment;

/**
 * Created by keeran on 2017/11/30.
 */

public class PersonalDataActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PersonalDataFragment();
    }
}
