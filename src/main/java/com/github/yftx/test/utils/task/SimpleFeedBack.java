package com.github.yftx.test.utils.task;

import android.content.Context;

/**
 * @author liuzl
 * @version 12-9-7
 */
public class SimpleFeedBack {
    public static void showLoading(Context context, String msg) {
        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, context).start(msg);
    }

    public static void hideLoading(Context context) {
        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, context).cancel();

    }

}
