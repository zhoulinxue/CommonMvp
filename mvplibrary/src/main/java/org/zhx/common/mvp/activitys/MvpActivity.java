package org.zhx.common.mvp.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.zhx.common.commonnetwork.commonokhttp.customObservable.api.CommonNetRequest;
import org.zhx.common.mvp.BasePresenter;
import org.zhx.common.mvp.api.PresenterApi;
import org.zhx.common.mvp.widgets.DialogApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseActivity
 * Author: zx
 * Date: 2020/1/22 11:28
 * Description:
 */
public abstract class MvpActivity<T extends BasePresenter> extends BaseActivity implements PresenterApi<T> {
    protected T mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public List<CommonNetRequest> getRequestList() {
        return mPresenter != null ? mPresenter.getRequests() : new ArrayList<CommonNetRequest>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
    }
}
