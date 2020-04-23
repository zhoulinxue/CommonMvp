package org.zhx.common.mvp;

import org.zhx.common.commonnetwork.commonokhttp.customObservable.api.BaseData;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseBeanRequstAdapter
 * Author: zx
 * Date: 2020/2/1 11:43
 * Description:
 */
public abstract class BaseBeanRequstAdapter<T> extends NetRequstAdapter<BaseData<T>, T> {
    public BaseBeanRequstAdapter(BaseMvpView mvpView) {
        super(mvpView);
    }

    public BaseBeanRequstAdapter(BaseMvpView mvpView, boolean isShowToast) {
        super(mvpView, isShowToast);
    }

    public BaseBeanRequstAdapter(BaseMvpView mvpView, boolean isShowToast, boolean isDismissDialog) {
        super(mvpView, isShowToast, isDismissDialog);
    }

    public BaseBeanRequstAdapter(boolean isDismissDialog, BaseMvpView mvpView) {
        super(isDismissDialog, mvpView);
    }

    @Override
    public final boolean onResult(BaseData<T> info) {
        return false;
    }
}
