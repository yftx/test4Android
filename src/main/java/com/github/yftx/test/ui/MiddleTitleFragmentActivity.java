package com.github.yftx.test.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.yftx.test.R;
import roboguice.inject.ContentView;

/**
 * @author yftx.net@gmail.com
 */

@ContentView(R.layout.middle_title_activity)
public class MiddleTitleFragmentActivity extends RoboSherlockActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View view = getLayoutInflater().inflate(R.layout.actionbar_title,null);
        ActionBar.LayoutParams actionBarParams = new ActionBar.LayoutParams(Gravity.CENTER);
        view.setLayoutParams(actionBarParams);
        getSupportActionBar().setCustomView(view);
    }
}