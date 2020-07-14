package org.zhx.common.mvp.uikit.impl;

import org.zhx.common.commonnetwork.api.BaseData;
import org.zhx.common.commonnetwork.api.CommonNetRequest;
import org.zhx.common.mvp.uikit.api.widgets.BaseMvpView;

import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseBeanRequstAdapter
 * Author: zx
 * Date: 2020/2/1 11:43
 * Description:
 */
public abstract class BaseDataRequstAdapter<T> extends NetRequstAdapter<BaseData<T>, T> {
    public BaseDataRequstAdapter(BaseMvpView mvpView) {
        super(mvpView);
    }

    public BaseDataRequstAdapter(BaseMvpView mvpView, boolean isShowToast) {
        super(mvpView, isShowToast);
    }

    public BaseDataRequstAdapter(BaseMvpView mvpView, boolean isShowToast, boolean isDismissDialog) {
        super(mvpView, isShowToast, isDismissDialog);
    }

    public BaseDataRequstAdapter(boolean isDismissDialog, BaseMvpView mvpView) {
        super(isDismissDialog, mvpView);
    }

    @Override
    public final boolean onResult(BaseData<T> info) {
        return false;
    }

    @Override
    public List<CommonNetRequest> getRequestList() {
        return mvpView.getRequestList();
    }
}
