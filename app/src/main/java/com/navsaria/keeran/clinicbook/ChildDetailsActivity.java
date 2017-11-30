package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/28.
 */

public class ChildDetailsActivity extends SingleFragmentActivity {

    public static final String EXTRA_CHILD_ID =
            "com.navsaria.keeran.clinicbook.child_id";
    public static final String TAG = "child_details_activity";

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.i(TAG, "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        String  uuid = getIntent().getSerializableExtra(EXTRA_CHILD_ID).toString();
        Log.i(TAG, "onStop() called" + uuid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }
}
