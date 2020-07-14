package org.zhx.common.mvp.uikit.impl;

import org.zhx.common.commonnetwork.api.CommonNetRequest;
import org.zhx.common.mvp.uikit.api.widgets.BaseMvpView;

import java.util.List;

/**
 * pakage :company.yinu.android.mvp
 * auther :zx
 * creatTime: 2019/8/1
 * description :
 */
public abstract class ObjectNetRequstAdapter<R> extends NetRequstAdapter<R, Void> {
    public ObjectNetRequstAdapter(BaseMvpView mvpView) {
        super(mvpView);
    }

    public ObjectNetRequstAdapter(BaseMvpView mvpView, boolean isShowToast) {
        super(mvpView, isShowToast);
    }

    public ObjectNetRequstAdapter(BaseMvpView mvpView, boolean isShowToast, boolean isDismissDialog) {
        super(mvpView, isShowToast, isDismissDialog);
    }

    public ObjectNetRequstAdapter(boolean isDismissDialog, BaseMvpView mvpView) {
        super(isDismissDialog, mvpView);
    }

    @Override
    public final boolean onResult(R info) {
        onResultData(info);
        return true;
    }

    protected abstract void onResultData(R info);

    @Override
    public final void onData(Void r) {

    }

    @Override
    public List<CommonNetRequest> getRequestList() {
        return mvpView.getRequestList();
    }
}
