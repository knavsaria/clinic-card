package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/30.
 */

public class PersonalDataActivity extends SingleFragmentActivity {

    private static final String EXTRA_CHILD_ID =
            "com.navsaria.keeran.clinicbook.child_id";

    public static Intent newIntent(Context packageContext, UUID childId) {
        Intent intent = new Intent(packageContext, PersonalDataActivity.class);
        intent.putExtra(EXTRA_CHILD_ID, childId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID childId = (UUID) getIntent().getSerializableExtra(EXTRA_CHILD_ID);
        return  PersonalDataFragment.newInstance(childId);
    }
}
