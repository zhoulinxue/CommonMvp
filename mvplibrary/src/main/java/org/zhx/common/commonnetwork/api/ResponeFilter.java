package org.zhx.common.commonnetwork.api;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.commonnetwork.api
 * @ClassName: ResponeFilter
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/11/27 15:36
 * @UpdateUser:
 * @UpdateDate: 2020/11/27 15:36
 * @UpdateRemark:
 * @Version:1.0
 */
public interface ResponeFilter {
    public void onError(String code, String message);

    public void onResult(Object object);
}
