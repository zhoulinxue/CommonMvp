package org.zhx.common.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * pakage :com.gaea.kiki.utils
 * auther :zx
 * creatTime: 2019/7/8
 * description :
 */
public class CommonFileCacheUtil {
    public static Map<String, String> map = new HashMap<>();
    private static String TAG = CommonFileCacheUtil.class.getSimpleName();
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "common";

    public static String getCacheSize(Context context) {
        File rootFile = getAPPRootFile(context);
        long fileSize = getFileSize(rootFile) + getFileSize(new File(context.getFilesDir().getAbsolutePath()));
        return formatFileSize(fileSize);
    }


    public static void clearCache(final Context context) {
        File rootFile = getAPPRootFile(context);
        clearCache(rootFile);
        clearCache(new File(context.getFilesDir().getAbsolutePath()));
    }


    public static File getAPPRootFile(Context context) {
        String state = Environment.getExternalStorageState();
        File rootDir = state.equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        return new File(rootDir.getAbsolutePath() + "/" + FILE_NAME);
    }


    /**
     * 递归
     */
    private static long getFileSize(File file) {
        long size = 0;
        File[] files = file.listFiles();
        if (files == null) {
            return 0;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getFileSize(files[i]);
            } else {
                size = size + files[i].length();
            }
        }
        return size;
    }


    private static void clearCache(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    files[i].delete();
                } else {
                    clearCache(files[i]);
                }
            }
        }

    }

    private static String formatFileSize(long bytes) {
        BigDecimal fileSize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = fileSize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1) {
            return returnValue + "MB";
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fileSize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return returnValue + "KB";
    }


    /**
     * 保存键值对
     *
     * @param context 上下文
     * @param key
     * @param valus
     */
    public static void save(Context context, String key, Object valus) {
        SharedPreferences.Editor editor = getEditorFromFile(context, FILE_NAME);
        if (valus == null) {
            Log.i(TAG, "key:" + key + " is null");
            return;
        }
        if (valus instanceof String) {
            editor.putString(key, (String) valus);
        } else if (valus instanceof Integer) {
            editor.putInt(key, (Integer) valus);
        } else if (valus instanceof Boolean) {
            editor.putBoolean(key, (Boolean) valus);
        } else if (valus instanceof Float) {
            editor.putFloat(key, (Float) valus);
        } else if (valus instanceof Long) {
            editor.putLong(key, (Long) valus);
        } else {
            editor.putString(key, JSONObject.toJSONString(valus));
        }
        editor.commit();
        Log.e(TAG, getObject(context, key, "保存数据失败"));
    }

    /**
     * @param context
     * @param fileName
     * @return
     */
    private static SharedPreferences.Editor getEditorFromFile(Context context, String fileName) {

        return getPreference(context, fileName).edit();
    }

    /**
     * @param context
     * @param fileName
     * @return
     */
    private static SharedPreferences getPreference(Context context, String fileName) {
        return context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
    }

    public static <T> T getObject(Context context, String key, T defaultValus) {
        return (T) get(context, key, defaultValus);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    private static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = getPreference(context, FILE_NAME);
        try {
            if (defaultObject instanceof String) {
                return sp.getString(key, (String) defaultObject);
            } else if (defaultObject instanceof Integer) {
                return sp.getInt(key, (Integer) defaultObject);
            } else if (defaultObject instanceof Boolean) {
                return sp.getBoolean(key, (Boolean) defaultObject);
            } else if (defaultObject instanceof Float) {
                return sp.getFloat(key, (Float) defaultObject);
            } else if (defaultObject instanceof Long) {
                return sp.getLong(key, (Long) defaultObject);
            } else {
                String msg = sp.getString(key, "");
                if (!TextUtils.isEmpty(msg)) {
                    return JSONObject.parseObject(msg, defaultObject.getClass());
                } else {
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return defaultObject;
        }
    }

    public static long shake(Context context, String key, long i) {
        return getObject(context, key, i);
    }
}
