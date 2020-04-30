package org.zhx.common.mvp.demo.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.zhx.common.mvp.activitys.BaseActivity;
import org.zhx.common.mvp.BasePresenter;
import org.zhx.common.mvp.demo.widgets.LoadingDialog;
import org.zhx.common.mvp.widgets.DialogApi;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseActivity
 * Author: zx
 * Date: 2020/1/22 11:28
 * Description:
 */
public abstract class MvpActivity<T extends BasePresenter> extends BaseActivity{
    protected T mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter=initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract T initPresenter();

    @Override
    public DialogApi creatLoadingDialog() {
        return new LoadingDialog(this);
    }
}
