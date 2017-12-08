package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by keeran on 2017/12/08.
 */

public class ImmunisationActivity extends SingleFragmentActivity {


    private static final String EXTRA_CHILD_ID =
            "com.navsaria.keeran.clinicbook.child_id";

    public static Intent newIntent(Context packageContext, UUID childId) {
        Intent intent = new Intent(packageContext, ImmunisationActivity.class);
        intent.putExtra(EXTRA_CHILD_ID, childId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID childId = (UUID) getIntent().getSerializableExtra(EXTRA_CHILD_ID);
        return  ImmunisationFragment.newInstance(childId);
    }


}
