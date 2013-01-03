package com.github.yftx.test.utils;

/**
 * @author yftx.net@gmail.com
 */
public class LogUtils {
    public  final static int NOT_LOG = -1;
    public  final static int VERBOSE = 0;
    public final static int DEBUG = 1;
    public final static int INFO = 2;
    public final static int WARN = 3;
    public final static int ERROR = 4;

    public static int level = NOT_LOG;

    private static final String TAG = "LogUtils";

    public static  void verbose(String message) {
        if (VERBOSE < level)
            return;
        android.util.Log.v(TAG, message);
    }

    public static void debug(String message) {
        if (DEBUG < level)
            return;
        android.util.Log.d(TAG, message);
    }

    public static void info(String message) {
        if (INFO < level)
            return;
        android.util.Log.i(TAG, message);
    }

    public static void warn(String message) {
        if (WARN < level)
            return;
        android.util.Log.w(TAG, message);
    }

    public static void error(String message) {
        if (ERROR < level)
            return;
        android.util.Log.e(TAG, message);
    }

    public static void set(int logLevel){
        level = logLevel;
    }
}
