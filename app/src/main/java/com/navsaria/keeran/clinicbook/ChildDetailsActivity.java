package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/28.
 */

public class ChildDetailsActivity extends SingleFragmentActivity {

    public static final String EXTRA_CHILD_ID =
            "com.navsaria.keeran.clinicbook.child_id";

    public static Intent newIntent(Context packagedContext, UUID childId) {
        Intent intent = new Intent(packagedContext, ChildDetailsActivity.class);
        intent.putExtra(EXTRA_CHILD_ID, childId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID childId = (UUID) getIntent().getSerializableExtra(EXTRA_CHILD_ID);
        return ChildDetailsFragment.newInstance(childId);
    }

}
