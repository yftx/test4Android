package com.github.yftx.test.ui;

import android.app.ActivityManager;
import android.os.Bundle;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.yftx.test.R;
import com.google.inject.Inject;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author yftx.net@gmail.com
 */

@ContentView(R.layout.show_phone_memory)
public class ShowPhoneMemoryActivity extends RoboSherlockActivity {

    @InjectView(R.id.tv_memory)
    TextView tvMemory;

    @Inject
    ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvMemory.setText("当前设备中每个app可用内存为 " + activityManager.getMemoryClass() + " M");
    }
}
