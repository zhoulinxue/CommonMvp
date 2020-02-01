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

    @Override
    public void onData(T t) {

    }

    @Override
    public boolean onResult(BaseBean<T> info) {
        return false;
    }
}
