package org.zhx.common.mvp;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseBeanRequstAdapter
 * Author: zx
 * Date: 2020/2/1 11:43
 * Description:
 */
public class BaseBeanRequstAdapter<T> extends NetRequstAdapter<BaseBean<T>, T> {
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
    public void onData(T t) {

    }

    @Override
    public final boolean onResult(BaseBean<T> info) {
        return false;
    }
}
