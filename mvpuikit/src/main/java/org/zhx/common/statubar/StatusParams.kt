package org.zhx.common.statubar

import android.database.ContentObserver
import android.graphics.Color
import android.graphics.Color.TRANSPARENT
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

/**
 * Copyright (C), 2015-2020
 * FileName: StatusParams
 * Author: zx
 * Date: 2020/5/29 16:40
 * Description:
 */
class StatusParams {

    var navigationBarView //4.4自定义一个导航栏
            : View? = null
    var navigationBarEnable = true //是否能修改导航栏颜色

    @FloatRange(from = 0.0, to = 1.0)
    var navigationBarAlpha = 0.0f //导航栏透明度


    @ColorInt
    var navigationBarColor = Color.BLACK //导航栏颜色

    @ColorInt
    var navigationBarColorTransform = Color.BLACK //导航栏变换后的颜色


    var navigationBarColorTemp: Int = navigationBarColor


   open var navigationBarWithKitkatEnable = true //是否能修改4.4手机导航栏颜色

    //emui3.1监听器
    var navigationStatusObserver
            : ContentObserver? = null


    var fullScreen = false //有导航栏的情况，全屏显示

    /**
     * 状态栏颜色
     * The Status bar color.
     */
    @ColorInt
    var statusBarColor = TRANSPARENT

    /**
     * 状态栏透明度
     * The Status bar alpha.
     */
    @FloatRange(from = 0.0, to = 1.0)
    var statusBarAlpha = 0.0f

    @FloatRange(from = 0.0, to = 1.0)
    var statusBarTempAlpha = 0.0f

    var statusBarFlag = true //是否可以修改状态栏颜色
    var statusBarView //4.4自定义一个状态栏
            : View? = null

    /**
     * 状态栏 深色 字体 标识
     * The Dark font.
     */
    var balckText = false

    /**
     * 是否可以修改状态栏颜色
     * The Status bar flag.
     */
    var statusBarColorEnabled = true

    /**
     * 状态栏变换后的颜色
     * The Status bar color transform.
     */
    @ColorInt
    var statusBarColorTransform = Color.BLACK

    /**
     * flymeOS状态栏字体变色
     * The Flyme os status bar font color.
     */
    @ColorInt
    var flymeOSTextColor = 0

    @ColorInt
    var flymeOSTextTempColor = 0

    /**
     * 解决软键盘与输入框冲突问题
     * The Keyboard enable.
     */
    var keyboardEnable = false

    /**
     * 软键盘属性
     * The Keyboard mode.
     */
    var keyboardMode = (WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
            or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    /**
     * 是否可以沉浸式
     * The Init enable.
     */
    var barEnable = true

    //软键盘监听类
    var onKeyboardListener
            : OnKeyboardListener? = null

    //软键盘监听类
    var keyboardPatch
            : InputHelper? = null


}