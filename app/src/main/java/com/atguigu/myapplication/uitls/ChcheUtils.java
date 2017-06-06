package com.atguigu.myapplication.uitls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HaoMeng on 2017/6/4.
 */

public class ChcheUtils {
    public static void setChche(Context context, String key, boolean isEnter) {
        SharedPreferences sp = context.getSharedPreferences("isEnter", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, isEnter).commit();
    }

    public static boolean getChche(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("isEnter", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}

