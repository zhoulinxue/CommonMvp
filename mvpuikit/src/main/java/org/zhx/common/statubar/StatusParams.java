package org.zhx.common.statubar;

import android.database.ContentObserver;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

/**
 * @ProjectName: commonStatuBar
 * @Package: org.zhx.common.statubar
 * @ClassName: StatusParams
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/9/3 17:52
 * @UpdateUser:
 * @UpdateDate: 2020/9/3 17:52
 * @UpdateRemark:
 * @Version:1.0
 */
public class StatusParams {


    View navigationBarView;//4.4自定义一个导航栏

    boolean navigationBarEnable = true;//是否能修改导航栏颜色

    @FloatRange(from = 0.0, to = 1.0)
    float navigationBarAlpha = 0.0f;//导航栏透明度


    @ColorInt
    int navigationBarColor = Color.BLACK;//导航栏颜色

    @ColorInt
    int navigationBarColorTransform = Color.BLACK;//导航栏变换后的颜色


    int navigationBarColorTemp = navigationBarColor;


    boolean navigationBarWithKitkatEnable = true;//是否能修改4.4手机导航栏颜色

    //emui3.1监听器
    ContentObserver navigationStatusObserver = null;


    boolean fullScreen = false;//有导航栏的情况，全屏显示

    /**
     * 状态栏颜色
     * The Status bar color.
     */
    @ColorInt
    int statusBarColor = Color.TRANSPARENT;

    /**
     * 状态栏透明度
     * The Status bar alpha.
     */
    @FloatRange(from = 0.0, to = 1.0)
    float statusBarAlpha = 0.0f;

    @FloatRange(from = 0.0, to = 1.0)
    float statusBarTempAlpha = 0.0f;

    boolean statusBarFlag = true;//是否可以修改状态栏颜色
    View statusBarView;//4.4自定义一个状态栏

    /**
     * 状态栏 深色 字体 标识
     * The Dark font.
     */
    boolean balckText = false;

    /**
     * 是否可以修改状态栏颜色
     * The Status bar flag.
     */
    boolean statusBarColorEnabled = true;

    /**
     * 状态栏变换后的颜色
     * The Status bar color transform.
     */
    @ColorInt
    int statusBarColorTransform = Color.BLACK;

    /**
     * flymeOS状态栏字体变色
     * The Flyme os status bar font color.
     */
    @ColorInt
    int flymeOSTextColor = 0;

    @ColorInt
    int flymeOSTextTempColor = 0;

    /**
     * 解决软键盘与输入框冲突问题
     * The Keyboard enable.
     */
    boolean keyboardEnable = false;

    /**
     * 软键盘属性
     * The Keyboard mode.
     */
    int keyboardMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;

    /**
     * 是否可以沉浸式
     * The Init enable.
     */
    boolean barEnable = true;//软键盘监听类
    OnKeyboardListener onKeyboardListener;//软键盘监听类
    InputHelper keyboardPatch;

    public View getNavigationBarView() {
        return navigationBarView;
    }

    public void setNavigationBarView(View navigationBarView) {
        this.navigationBarView = navigationBarView;
    }

    public boolean getNavigationBarEnable() {
        return navigationBarEnable;
    }

    public void setNavigationBarEnable(boolean navigationBarEnable) {
        this.navigationBarEnable = navigationBarEnable;
    }

    public float getNavigationBarAlpha() {
        return navigationBarAlpha;
    }

    public void setNavigationBarAlpha(float navigationBarAlpha) {
        this.navigationBarAlpha = navigationBarAlpha;
    }

    public int getNavigationBarColor() {
        return navigationBarColor;
    }

    public void setNavigationBarColor(int navigationBarColor) {
        this.navigationBarColor = navigationBarColor;
    }

    public int getNavigationBarColorTransform() {
        return navigationBarColorTransform;
    }

    public void setNavigationBarColorTransform(int navigationBarColorTransform) {
        this.navigationBarColorTransform = navigationBarColorTransform;
    }

    public int getNavigationBarColorTemp() {
        return navigationBarColorTemp;
    }

    public void setNavigationBarColorTemp(int navigationBarColorTemp) {
        this.navigationBarColorTemp = navigationBarColorTemp;
    }

    public boolean getNavigationBarWithKitkatEnable() {
        return navigationBarWithKitkatEnable;
    }

    public void setNavigationBarWithKitkatEnable(boolean navigationBarWithKitkatEnable) {
        this.navigationBarWithKitkatEnable = navigationBarWithKitkatEnable;
    }

    public ContentObserver getNavigationStatusObserver() {
        return navigationStatusObserver;
    }

    public void setNavigationStatusObserver(ContentObserver navigationStatusObserver) {
        this.navigationStatusObserver = navigationStatusObserver;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    public void setStatusBarColor(int statusBarColor) {
        this.statusBarColor = statusBarColor;
    }

    public float getStatusBarAlpha() {
        return statusBarAlpha;
    }

    public void setStatusBarAlpha(float statusBarAlpha) {
        this.statusBarAlpha = statusBarAlpha;
    }

    public float getStatusBarTempAlpha() {
        return statusBarTempAlpha;
    }

    public void setStatusBarTempAlpha(float statusBarTempAlpha) {
        this.statusBarTempAlpha = statusBarTempAlpha;
    }

    public boolean isStatusBarFlag() {
        return statusBarFlag;
    }

    public void setStatusBarFlag(boolean statusBarFlag) {
        this.statusBarFlag = statusBarFlag;
    }

    public View getStatusBarView() {
        return statusBarView;
    }

    public void setStatusBarView(View statusBarView) {
        this.statusBarView = statusBarView;
    }

    public boolean getBalckText() {
        return balckText;
    }

    public void setBalckText(boolean balckText) {
        this.balckText = balckText;
    }

    public boolean isStatusBarColorEnabled() {
        return statusBarColorEnabled;
    }

    public void setStatusBarColorEnabled(boolean statusBarColorEnabled) {
        this.statusBarColorEnabled = statusBarColorEnabled;
    }

    public int getStatusBarColorTransform() {
        return statusBarColorTransform;
    }

    public void setStatusBarColorTransform(int statusBarColorTransform) {
        this.statusBarColorTransform = statusBarColorTransform;
    }

    public int getFlymeOSTextColor() {
        return flymeOSTextColor;
    }

    public void setFlymeOSTextColor(int flymeOSTextColor) {
        this.flymeOSTextColor = flymeOSTextColor;
    }

    public int getFlymeOSTextTempColor() {
        return flymeOSTextTempColor;
    }

    public void setFlymeOSTextTempColor(int flymeOSTextTempColor) {
        this.flymeOSTextTempColor = flymeOSTextTempColor;
    }

    public boolean getKeyboardEnable() {
        return keyboardEnable;
    }

    public void setKeyboardEnable(boolean keyboardEnable) {
        this.keyboardEnable = keyboardEnable;
    }

    public int getKeyboardMode() {
        return keyboardMode;
    }

    public void setKeyboardMode(int keyboardMode) {
        this.keyboardMode = keyboardMode;
    }

    public boolean isBarEnable() {
        return barEnable;
    }

    public void setBarEnable(boolean barEnable) {
        this.barEnable = barEnable;
    }

    public OnKeyboardListener getOnKeyboardListener() {
        return onKeyboardListener;
    }

    public void setOnKeyboardListener(OnKeyboardListener onKeyboardListener) {
        this.onKeyboardListener = onKeyboardListener;
    }

    public InputHelper getKeyboardPatch() {
        return keyboardPatch;
    }

    public void setKeyboardPatch(InputHelper keyboardPatch) {
        this.keyboardPatch = keyboardPatch;
    }
}
