package org.zhx.common.mvp.uikit.refresh.api;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.uikit.refresh
 * @ClassName: SwipeRefreshTrigger
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/6/8 17:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/8 17:41
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public interface SwipeRefreshTrigger {
    /**
     * @method
     * @description  滑动刷新时调用
     * @date: 2020/6/8 17:41
     * @author: zhouxue
     * @param 
     * @return 
     */
    void onRefresh();
}
