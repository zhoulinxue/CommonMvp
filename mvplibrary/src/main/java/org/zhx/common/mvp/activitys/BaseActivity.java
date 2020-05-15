package org.zhx.common.mvp.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.zhx.common.mvp.api.ViewCreatApi;
import org.zhx.common.mvp.widgets.BaseMvpView;
import org.zhx.common.mvp.widgets.DialogApi;


/**
 * Copyright (C), 2015-2020
 * FileName: BaseActivity
 * Author: zx
 * Date: 2020/1/22 10:34
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView, ViewCreatApi {
    private DialogApi mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoading = creatLoadingDialog();
        int layout = initLayout();
        if (layout != 0) {
            View view =getLayoutInflater().inflate(layout,null);
            setContentView(view);
        }
        if (getIntent() != null)
            onLoadIntentData(getIntent());
        onCreatView();
        if (savedInstanceState != null)
            onLoadDataFromSavedInstanceState(savedInstanceState);
        onLoadContent();
    }

    @Override
    public void onLoadIntentData(Intent intent) {

    }

    @Override
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onLoadIntentData(intent);
    }


    @Override
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showToast(int res) {
        if (res != 0) {
            showToast(getResources().getString(res));
        }
    }

    @Override
    public void showLoadingDialog() {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.showLoading();
        }
    }

    @Override
    public void showLoadingDialog(int resId) {
        if (mLoading != null) {
            if (resId != 0)
                mLoading.setMessage(getResources().getString(resId));
            showLoadingDialog();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismissloading();
        }
    }

    @Override
    public void onError(String code, String msg) {
        showToast(msg);
    }

    @Override
    public Context getContext() {
        return this;
    }

}
