package org.zhx.common.mvp.uikit.activitys;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.zhx.common.commonnetwork.api.CommonNetRequest;
import org.zhx.common.mvp.BasePresenter;
import org.zhx.common.mvp.api.PresenterApi;

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
        Log.e(TAG, "initPresenter()..");
        super.onCreate(savedInstanceState);
    }

    @Override
    public List<CommonNetRequest> getRequestList() {
        return mPresenter != null ? mPresenter.getRequests() : new ArrayList<CommonNetRequest>();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
    }
}
