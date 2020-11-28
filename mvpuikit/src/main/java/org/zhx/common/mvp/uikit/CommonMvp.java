package org.zhx.common.mvp.uikit;

import org.zhx.common.commonnetwork.CommonOkHttp;
import org.zhx.common.commonnetwork.api.ResponeFilter;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.uikit
 * @ClassName: CommonMvp
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2020/11/27 15:35
 * @UpdateUser:
 * @UpdateDate: 2020/11/27 15:35
 * @UpdateRemark:
 * @Version:1.0
 */
public class CommonMvp {
    public static void init(ResponeFilter filter) {
        CommonOkHttp.init(filter);
    }
}
