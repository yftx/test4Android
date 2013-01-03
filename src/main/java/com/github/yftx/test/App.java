package com.github.yftx.test;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.github.yftx.test.utils.ContextManager;
import com.github.yftx.test.utils.LogUtils;

/**
 * @author yftx.net@gmail.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextManager.setContext(getBaseContext());
        readConfig();
    }

    private void readConfig() {
        Integer logLevel;
        try {
            PackageManager pm = this.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            logLevel = (Integer) ai.metaData.get("LOG_LEVEL");
        } catch (Exception e) {
            logLevel = LogUtils.NOT_LOG;
        }
        LogUtils.set(logLevel);
    }
}
