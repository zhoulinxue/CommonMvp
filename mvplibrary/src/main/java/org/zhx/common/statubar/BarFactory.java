package org.zhx.common.statubar;

import android.app.Activity;

import java.util.HashMap;

/**
 * @ProjectName: commonStatuBar
 * @Package: org.zhx.common.statubar
 * @ClassName: BarFactory
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/9/3 17:49
 * @UpdateUser:
 * @UpdateDate: 2020/9/3 17:49
 * @UpdateRemark:
 * @Version:1.0
 */
public class BarFactory {
    private  static HashMap<String, CommonStatusBar> map = new HashMap();

    public static CommonStatusBar createStatusBar(Activity activity) {
        String tag = getTag(activity);
        CommonStatusBar bar = map.get(tag);
        if (bar == null) {
            bar = new CommonStatusBar(activity);
            map.put(tag, bar);
        }
        return bar;
    }

    public static String getTag(Activity activity) {
        return System.identityHashCode(activity) + "";
    }
}
