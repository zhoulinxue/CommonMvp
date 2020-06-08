package org.zhx.common.mvp.uikit.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.uikit.refresh
 * @ClassName: SwipeRefreshHeaderLayout
 * @Description:java 拖动刷新 头部布局
 * @Author: zhouxue
 * @CreateDate: 2020/6/8 17:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/8 17:44
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class SwipeRefreshHeaderLayout extends FrameLayout implements SwipeRefreshTrigger, SwipeTrigger {
    public SwipeRefreshHeaderLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshHeaderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeRefreshHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
    }
}
