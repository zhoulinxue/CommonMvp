package org.zhx.common.mvp.uikit.utils;

import android.content.Context;

import org.zhx.common.mvp.uikit.R;

import java.lang.reflect.Field;

/**
 * Copyright (C), 2015-2020
 * FileName: StatuBarUtil
 * Author: zx
 * Date: 2020/4/22 16:54
 * Description:
 */
public class StatuBarUtil {
    public static int statusbarheight;

    public static int getStatusBarHeight(Context context) {
        if (statusbarheight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusbarheight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statusbarheight == 0) {
            statusbarheight = dip2px(context, 25);
        }
        return statusbarheight;
    }
    public static int getTitlebarHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.y100);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
