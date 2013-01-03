package com.github.yftx.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.yftx.test.R;
import roboguice.inject.ContentView;


@ContentView(R.layout.main)
public class HomeActivity extends RoboSherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void openAutoCompeleteDemo(View v) {
        startActivity(new Intent(this,TestAutoCompleteDemo.class));
        finish();
        //两个参数分别表示进入的动画,退出的动画
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}

