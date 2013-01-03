package com.github.yftx.test.utils.task;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;


/**
 * 管理所有的task，方便在程序退出的时候终止所有运行的task
 */
public class TaskManager extends Observable {
    private static final String TAG = "TaskManager";

    public static final Integer CANCEL_ALL = 1;

    public void cancelAll() {
        Log.d(TAG, "All task Cancelled.");
        setChanged();
        notifyObservers(CANCEL_ALL);
    }

    public void addTask(Observer task) {
        super.addObserver(task);
    }

    public void removeTask(Observer task) {
        super.deleteObserver(task);
    }
}