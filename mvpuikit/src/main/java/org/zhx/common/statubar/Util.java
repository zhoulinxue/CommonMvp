package org.zhx.common.statubar;

import android.os.Build;

/**
 * Copyright (C), 2015-2020
 * FileName: Util
 * Author: zx
 * Date: 2020/5/29 16:40
 * Description:
 */
public class Util {
    /**
     * 判断手机支不支持状态栏字体变色
     * Is support status bar dark font boolean.
     *
     * @return the boolean
     */
    public static boolean isSupportChangeTextColor() {
        if (OSUtils.isMIUI6Later() || OSUtils.isFlymeOS4Later()
                || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            return true;
        } else
            return false;
    }
}
