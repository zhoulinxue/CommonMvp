package org.zhx.common.mvp.uikit.refresh.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.zhx.common.mvp.uikit.refresh.api.SwipeLoadMoreTrigger;
import org.zhx.common.mvp.uikit.refresh.api.SwipeTrigger;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.uikit.refresh
 * @ClassName: SwipeLoadMoreFooterLayout
 * @Description:java 加载更多  脚 布局文件
 * @Author: zhouxue
 * @CreateDate: 2020/6/8 17:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/8 17:43
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class SwipeLoadMoreFooterLayout extends FrameLayout implements SwipeLoadMoreTrigger, SwipeTrigger {

    public SwipeLoadMoreFooterLayout(Context context) {
        this(context, null);
    }

    public SwipeLoadMoreFooterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLoadMoreFooterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
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
