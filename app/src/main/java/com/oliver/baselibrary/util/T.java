package com.oliver.baselibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * description：//todo
 * author：oliver
 * email：qiwei0727@163.com
 * date：on 2017/11/19
 */

public class T {
    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
