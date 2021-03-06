package org.zhx.common.commonnetwork.customObservable;

import org.zhx.common.commonnetwork.api.CommonExcutable;
import org.zhx.common.commonnetwork.api.CommonNetRequest;
import org.zhx.common.commonnetwork.api.CommonNetRequestCallBack;

import io.reactivex.Observable;

/**
 * Copyright (C), 2015-2019
 * FileName: CommonOkExcutor
 * Author: zx
 * Date: 2019/12/18 14:37
 * Description:
 */
public class CommonOkExcutor<T> implements CommonExcutable {
    Observable<T> observable;
    private CommonNetRequestCallBack<?, ?> netRequstAdapter;

    public CommonOkExcutor(Observable<T> observable) {
        this.observable = observable;
    }

    @Override
    public CommonNetRequest excute() {
        return new CommonOkHttpRequest<>(observable, netRequstAdapter).start();
    }

    public void setNetRequstAdapter(CommonNetRequestCallBack<?, ?> netRequstAdapter) {
        this.netRequstAdapter = netRequstAdapter;
    }
}
