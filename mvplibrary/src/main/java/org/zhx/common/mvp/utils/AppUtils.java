package org.zhx.common.mvp.utils;

//跟App相关的辅助类

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AppUtils {

    /**
     * 获取应用程序名称
     */

    public static synchronized String getAppName(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            int labelRes = packageInfo.applicationInfo.labelRes;

            return context.getResources().getString(labelRes);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    /**
     * 获取版本名称，如1.0.0
     * @param context
     * @return
     */

    public static synchronized String getVersionName(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            return packageInfo.versionName;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    /**
     * 获取版本code
     * @param context
     * @return
     */

    public static synchronized int getVersionCode(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            return packageInfo.versionCode;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    /**
     * 获取应用包名
     * @param context
     * @return
     */

    public static synchronized String getPackageName(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            return packageInfo.packageName;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    /**
     * 获取应用图标 bitmap
     *
     * @param context
     */

    public static synchronized Bitmap getBitmap(Context context) {

        PackageManager packageManager = null;

        ApplicationInfo applicationInfo = null;

        try {

            packageManager = context.getApplicationContext()

                    .getPackageManager();

            applicationInfo = packageManager.getApplicationInfo(

                    context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {

            applicationInfo = null;

        }

        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable

        BitmapDrawable bd = (BitmapDrawable) d;

        Bitmap bm = bd.getBitmap();

        return bm;

    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 判断 activity 主题 是否为透明主题
     *
     * @param context
     * @return
     */
    public static boolean isTranslucentOrFloating(Context context) {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = context.obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    public static String getAndroidId(Context context) {
        String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return ANDROID_ID;
    }

    public static boolean fixOrientation(Activity activity) {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(activity);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

