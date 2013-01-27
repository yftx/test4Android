package com.github.yftx.test.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        writeToPreference();
    }

    /**
     * 为测试从管理程序界面进入到app设定的界面做假数据
     * 只有当一个app中有缓存数据时,才可以从app列表中点击管理缓存按钮
     */
    private void writeToPreference() {

        SharedPreferences settings = this.getSharedPreferences("sdw", 0);
          SharedPreferences.Editor editor = settings.edit();
          editor.putBoolean("autoLogin", true);
          editor.commit();
    }


    //自动补全输入框的例子,异步加载查询结果
    public void openAutoCompeleteDemo(View v) {
        startActivity(new Intent(this, TestAutoCompleteDemo.class));
        //两个参数分别表示进入的动画,退出的动画
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    //title 在中间的actionbar
    public void openMiddleTitleActivity(View v) {
        startActivity(new Intent(this, MiddleTitleFragmentActivity.class));
        //两个参数分别表示进入的动画,退出的动画
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }

    //显示每个应用可用的内存大小
    public void openShowPhoneMemeoryActivity(View v) {
        startActivity(new Intent(this, ShowPhoneMemoryActivity.class));
        overridePendingTransition(R.anim.slide_in_top, R.anim.fade_out);
    }
}

