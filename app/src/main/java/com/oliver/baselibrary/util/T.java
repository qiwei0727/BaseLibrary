package com.oliver.baselibrary.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * description：//todo
 * author：oliver
 * email：qiwei0727@163.com
 * date：on 2017/11/19
 */

public class T {
    private static Toast toast;

    public static void showToast(final Activity context,
                                 final String message) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }

        //在主线程
        if ("main".equals(Thread.currentThread().getName())) {
            toast.show();
        } else {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        }
    }

}
