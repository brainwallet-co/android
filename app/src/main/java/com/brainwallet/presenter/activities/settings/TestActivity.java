package com.brainwallet.presenter.activities.settings;

import android.os.Bundle;

import com.brainwallet.R;
import com.brainwallet.presenter.activities.util.BRActivity;

public class TestActivity extends BRActivity {
    private static final String TAG = TestActivity.class.getName();


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
