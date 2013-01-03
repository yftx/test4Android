package com.github.yftx.test.utils.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public abstract class TaskFeedback {
    private static TaskFeedback _instance = null;

    public static final int DIALOG_MODE = 0x01;
    public static final int REFRESH_MODE = 0x02;
    public static final int PROGRESS_MODE = 0x03;

    public static TaskFeedback getInstance(int type, Context context) {
        switch (type) {
            case DIALOG_MODE:
                _instance = DialogFeedback.getInstance();
                break;
//            case PROGRESS_MODE:
//                _instance = ProgressBarFeedback.getInstance();
        }
        _instance.setContext(context);
        return _instance;
    }

    protected Context _context;

    protected void setContext(Context context) {
        _context = context;
    }

    public Context getContent() {
        return _context;
    }

    // default do nothing
    public void start(String prompt) {
    }

    public void cancel() {
    }


    public void success(String prompt) {
    }


    public void success() {
        success("");
    }


    public void failed(String prompt) {
    }


    public void showProgress(int progress) {
    }

}


class DialogFeedback extends TaskFeedback {
    private static DialogFeedback _instance = null;

    public static DialogFeedback getInstance() {
        if (_instance == null) {
            _instance = new DialogFeedback();
        }
        return _instance;
    }

    private ProgressDialog _dialog = null;

    @Override
    public void cancel() {
        if (_dialog != null) {
            _dialog.dismiss();
        }
    }

    @Override
    public void failed(String prompt) {
        if (_dialog != null) {
            _dialog.dismiss();
        }

        Toast toast = Toast.makeText(_context, prompt, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void start(String prompt) {
        _dialog = ProgressDialog.show(_context, "", prompt, true);
        _dialog.setCancelable(false);
    }

    @Override
    public void success(String prompt) {
        if (_dialog != null) {
            _dialog.dismiss();
        }
    }
}
