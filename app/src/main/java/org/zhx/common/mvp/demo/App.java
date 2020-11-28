package org.zhx.common.mvp.demo;

import android.app.Application;

import org.zhx.common.commonnetwork.impl.ResponeFilterAdapter;
import org.zhx.common.mvp.uikit.CommonMvp;

/**
 * @ProjectName: CommonMvp
 * @Package: PACKAGE_NAME
 * @ClassName: org.zhx.common.mvp.demo.App
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2020/11/28 11:02
 * @UpdateUser:
 * @UpdateDate: 2020/11/28 11:02
 * @UpdateRemark:
 * @Version:1.0
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonMvp.init(new ResponeFilterAdapter(){
            /**
             *  使用场景  当 接口报  session  过期 时  统一处理 这类型错误
             * @param code 错误 code
             * @param message 错误信息
             * @return  是否 拦截传递  默认是false  当 返回为 true  时 不在调用presenter 中onError
             */
            @Override
            public boolean onError(String code, String message) {
                //拦截 所有错误信息
                return super.onError(code, message);
            }
        });
    }
}
