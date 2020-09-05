package org.zhx.common.statubar;

import android.app.Activity;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommonStatusBar {
    private Activity mActivity;
    /**
     * 用户配置的bar参数
     */
    public StatusParams mBarParams;
    private BarConfig mConfig;

    private Window mWindow;
    private ViewGroup mDecorView;
    private ViewGroup mContentView;
    public CommonStatusBar(Activity mActivity) {
        this.mActivity = mActivity;
        initCommonParameter(mActivity.getWindow());
    }

    /**
     * 初始化共同参数
     *
     * @param window window
     */
    private void initCommonParameter(Window window) {
        mWindow = window;
        mBarParams = new StatusParams();
        mDecorView = (ViewGroup) mWindow.getDecorView();
        mContentView = mDecorView.findViewById(android.R.id.content);
        initParams();
    }

    public static CommonStatusBar acticity(Activity activity) {
        return BarFactory.createStatusBar(activity);
    }

    /**
     * 初始化沉浸式默认参数
     * Init params.
     */
    private void initParams() {
        mDecorView = (ViewGroup) mWindow.getDecorView();
        mContentView = mDecorView.findViewById(android.R.id.content);
        mConfig = new BarConfig(mActivity);
        mBarParams = new StatusParams();
    }

    public CommonStatusBar whiteText() {
        textColor(false);
        return this;
    }

    public CommonStatusBar blackText() {
        textColor(true);
        return this;
    }

    private void textColor(boolean isblack) {
        mBarParams.setBalckText(isblack);
        if (!isblack) {
            mBarParams.setFlymeOSTextColor(0);
        }
        if (Util.isSupportChangeTextColor()) {
            mBarParams.setStatusBarAlpha(0);
        } else {
            mBarParams.setStatusBarAlpha(0.2f);
        }
    }

    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是true
     */
    public CommonStatusBar bottomInput() {
        mBarParams.setKeyboardEnable(true);
        mBarParams.setKeyboardMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return this;
    }

    public void set() {
        init();   //初始化沉浸式
        keyboardEnable();  //解决软键盘与底部输入框冲突问题
        registerEMUI3_x();  //解决华为emui3.1或者3.0导航栏手动隐藏的问题
    }

    private static final String NAVIGATIONBAR_IS_MIN = "navigationbar_is_min";

    /**
     * 注册emui3.x导航栏监听函数
     * Register emui 3 x.
     */
    private void registerEMUI3_x() {
        if (OSUtils.isEMUI3_x() && mConfig.hasNavigtionBar()
                && mBarParams.getNavigationBarEnable() && mBarParams.getNavigationBarWithKitkatEnable()) {
            if (mBarParams.getNavigationStatusObserver() == null && mBarParams.getNavigationBarView() != null) {
                mBarParams.setNavigationStatusObserver(new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange) {
                        int navigationBarIsMin = Settings.System.getInt(mActivity.getContentResolver(),
                                NAVIGATIONBAR_IS_MIN, 0);
                        if (navigationBarIsMin == 1) {
                            //导航键隐藏了
                            mBarParams.getNavigationBarView().setVisibility(View.GONE);
                            mContentView.setPadding(0, mContentView.getPaddingTop(), 0, 0);
                        } else {
                            //导航键显示了
                            mBarParams.getNavigationBarView().setVisibility(View.VISIBLE);
                            if (mConfig.isNavigationAtBottom())
                                mContentView.setPadding(0, mContentView.getPaddingTop(), 0, mConfig.getNavigationBarHeight());
                            else
                                mContentView.setPadding(0, mContentView.getPaddingTop(), mConfig.getNavigationBarWidth(), 0);
                        }
                    }
                });
            }
            mActivity.getContentResolver().registerContentObserver(Settings.System.getUriFor
                    (NAVIGATIONBAR_IS_MIN), true, mBarParams.getNavigationStatusObserver());
        }
    }

    /**
     * 解决底部输入框与软键盘问题
     * Keyboard enable.
     */
    private void keyboardEnable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mBarParams.getKeyboardPatch() == null) {
                mBarParams.setKeyboardPatch(InputHelper.patch(mActivity, mWindow));
            }
            mBarParams.getKeyboardPatch().setBarParams(mBarParams);
            if (mBarParams.getKeyboardEnable()) {  //解决软键盘与底部输入框冲突问题
                mBarParams.getKeyboardPatch().enable(mBarParams.getKeyboardMode());
            } else {
                mBarParams.getKeyboardPatch().disable(mBarParams.getKeyboardMode());
            }
        }
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;  //防止系统栏隐藏时内容区域大小发生变化
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !OSUtils.isEMUI3_1()) {
                uiFlags = initBarAboveLOLLIPOP(uiFlags); //初始化5.0以上，包含5.0
                uiFlags = setStatusBarDarkFont(uiFlags); //android 6.0以上设置状态栏字体为暗色
            } else {
                initBarBelowLOLLIPOP(); //初始化5.0以下，4.4以上沉浸式
            }
            uiFlags = hideBar(uiFlags);  //隐藏状态栏或者导航栏
            mWindow.getDecorView().setSystemUiVisibility(uiFlags);
        }
        if (OSUtils.isMIUI6Later())
            setMIUIStatusBarDarkFont(mWindow, mBarParams.getBalckText());         //修改miui状态栏字体颜色
        if (OSUtils.isFlymeOS4Later()) {          // 修改Flyme OS状态栏字体颜色
            if (mBarParams.getFlymeOSTextColor() != 0) {
                FlymeOSTextUtils.setStatusBarDarkIcon(mActivity, mBarParams.getFlymeOSTextColor());
            } else {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                    FlymeOSTextUtils.setStatusBarDarkIcon(mActivity, mBarParams.getStatusBarColor());
            }
        }
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @return boolean 成功执行返回true
     */
    private void setMIUIStatusBarDarkFont(Window window, boolean darkFont) {
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (darkFont) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Hide bar.
     * 隐藏或显示状态栏和导航栏。
     *
     * @param uiFlags the ui flags
     * @return the int
     */

    private int hideBar(int uiFlags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            uiFlags |= View.SYSTEM_UI_FLAG_VISIBLE;
        }
        return uiFlags | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }

    /**
     * 初始化android 5.0以上状态栏和导航栏
     *
     * @param uiFlags the ui flags
     * @return the int
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int initBarAboveLOLLIPOP(int uiFlags) {
        uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;  //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态栏遮住。
        if (mBarParams.isFullScreen() && mBarParams.getNavigationBarEnable()) {
            uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION; //Activity全屏显示，但导航栏不会被隐藏覆盖，导航栏依然可见，Activity底部布局部分会被导航栏遮住。
        }
        mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (mConfig.hasNavigtionBar()) {  //判断是否存在导航栏
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  //需要设置这个才能设置状态栏颜色
        if (mBarParams.isStatusBarFlag())
            mWindow.setStatusBarColor(ColorUtils.blendARGB(mBarParams.getStatusBarColor(),
                    mBarParams.getStatusBarColorTransform(), mBarParams.getStatusBarAlpha()));  //设置状态栏颜色
        else
            mWindow.setStatusBarColor(ColorUtils.blendARGB(mBarParams.getStatusBarColor(),
                    Color.TRANSPARENT, mBarParams.getStatusBarAlpha()));  //设置状态栏颜色
        if (mBarParams.getNavigationBarEnable())
            mWindow.setNavigationBarColor(ColorUtils.blendARGB(mBarParams.getNavigationBarColor(),
                    mBarParams.getNavigationBarColorTransform(), mBarParams.getNavigationBarAlpha()));  //设置导航栏颜色
        return uiFlags;
    }

    /**
     * Sets status bar dark font.
     * 设置状态栏字体颜色，android6.0以上
     */
    private int setStatusBarDarkFont(int uiFlags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mBarParams.getBalckText()) {
            return uiFlags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            return uiFlags;
        }
    }

    /**
     * 初始化android 4.4和emui3.1状态栏和导航栏
     */
    private void initBarBelowLOLLIPOP() {
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
        setupStatusBarView(); //创建一个假的状态栏
        if (mConfig.hasNavigtionBar()) {  //判断是否存在导航栏，是否禁止设置导航栏
            if (mBarParams.getNavigationBarEnable() && mBarParams.getNavigationBarWithKitkatEnable())
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏，设置这个，如果有导航栏，底部布局会被导航栏遮住
            else
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            setupNavBarView();   //创建一个假的导航栏
        }
    }

    /**
     * 设置一个可以自定义颜色的导航栏
     */
    private void setupNavBarView() {
        if (mBarParams.getNavigationBarView() == null) {
            mBarParams.setNavigationBarView(new View(mActivity));
        }
        FrameLayout.LayoutParams params;
        if (mConfig.isNavigationAtBottom()) {
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, mConfig.getNavigationBarHeight());
            params.gravity = Gravity.BOTTOM;
        } else {
            params = new FrameLayout.LayoutParams(mConfig.getNavigationBarWidth(), FrameLayout.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.END;
        }
        mBarParams.getNavigationBarView().setLayoutParams(params);
        if (mBarParams.getNavigationBarEnable() && mBarParams.getNavigationBarWithKitkatEnable()) {
            if (!mBarParams.isFullScreen() && (mBarParams.getNavigationBarColorTransform() == Color.TRANSPARENT)) {
                mBarParams.getNavigationBarView().setBackgroundColor(ColorUtils.blendARGB(mBarParams.getNavigationBarColor(),
                        Color.BLACK, mBarParams.getNavigationBarAlpha()));
            } else {
                mBarParams.getNavigationBarView().setBackgroundColor(ColorUtils.blendARGB(mBarParams.getNavigationBarColor(),
                        mBarParams.getNavigationBarColorTransform(), mBarParams.getNavigationBarAlpha()));
            }
        } else
            mBarParams.getNavigationBarView().setBackgroundColor(Color.TRANSPARENT);
        mBarParams.getNavigationBarView().setVisibility(View.VISIBLE);
        ViewGroup viewGroup = (ViewGroup) mBarParams.getNavigationBarView().getParent();
        if (viewGroup != null)
            viewGroup.removeView(mBarParams.getNavigationBarView());
        mDecorView.addView(mBarParams.getNavigationBarView());
    }


    /**
     * 设置一个可以自定义颜色的状态栏
     */
    private void setupStatusBarView() {
        if (mBarParams.getStatusBarView() == null) {
            mBarParams.setStatusBarView(new View(mActivity));
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                mConfig.getStatusBarHeight());
        params.gravity = Gravity.TOP;
        mBarParams.getStatusBarView().setLayoutParams(params);
        if (mBarParams.isStatusBarFlag())
            mBarParams.getStatusBarView().setBackgroundColor(ColorUtils.blendARGB(mBarParams.getStatusBarColor(),
                    mBarParams.getStatusBarColorTransform(), mBarParams.getStatusBarAlpha()));
        else
            mBarParams.getStatusBarView().setBackgroundColor(ColorUtils.blendARGB(mBarParams.getStatusBarColor(),
                    Color.TRANSPARENT, mBarParams.getStatusBarAlpha()));
        mBarParams.getStatusBarView().setVisibility(View.VISIBLE);
        ViewGroup viewGroup = (ViewGroup) mBarParams.getStatusBarView().getParent();
        if (viewGroup != null)
            viewGroup.removeView(mBarParams.getStatusBarView());
        mDecorView.addView(mBarParams.getStatusBarView());
    }
}
