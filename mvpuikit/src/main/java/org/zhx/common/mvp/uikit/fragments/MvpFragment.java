package org.zhx.common.mvp.uikit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.zhx.common.commonnetwork.api.CommonNetRequest;
import org.zhx.common.mvp.BasePresenter;
import org.zhx.common.mvp.api.PresenterApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: MvpFragment
 * Author: zx
 * Date: 2020/5/19 11:47
 * Description:
 */
public abstract class MvpFragment<T extends BasePresenter> extends BaseFragment implements PresenterApi<T> {
    protected T mPresenter;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
    }

    @Override
    public List<CommonNetRequest> getRequestList() {
        return mPresenter!=null?mPresenter.getRequests():new ArrayList<>();
    }
}
