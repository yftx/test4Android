package com.github.yftx.test.ui;

import android.app.Activity;
import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.yftx.test.R;
import roboguice.inject.ContentView;

/**
 * 从应用界面进入管理app存储空间的activity
 * 需要注意的是在Amanifest.xml中的application标签下需要配置
 * android:manageSpaceActivity="com.github.yftx.test.ui.AppSettingActivity"
 * 需要注意的是此处的启动activity的名字需要配成全路径的.
 * 并且同样需要使用activity标签声明.
 * <activity android:name=".ui.AppSettingActivity">
 *       <meta-data
 *          android:name="category"
 *          android:resource="@string/app_setting_lable"/>
 *       <intent-filter>
 *          <action android:name="com.todoroo.astrid.SETTINGS"/>
 *          <category android:name="android.intent.category.DEFAULT"/>
 *       </intent-filter>
 * </activity>
 *
 * @author yftx.net@gmail.com
 */

@ContentView(R.layout.app_setting_activity)
public class AppSettingActivity extends RoboSherlockActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}