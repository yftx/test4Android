package com.github.yftx.test.utils;

import android.widget.Toast;

/**
 * @author liuzl
 */
public class InfoUtils {
    public static void showMsgShort(int msgID){
        Toast.makeText(ContextManager.getContext(),ContextManager.getString(msgID),Toast.LENGTH_SHORT).show();
    }

    public static void showMsgLong(int msgID){
        Toast.makeText(ContextManager.getContext(),ContextManager.getString(msgID),Toast.LENGTH_LONG).show();

    }

    public static void showMsgShort(String msg){
        Toast.makeText(ContextManager.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void showMsgLong(String msg){
        Toast.makeText(ContextManager.getContext(),msg,Toast.LENGTH_LONG).show();

    }


}
