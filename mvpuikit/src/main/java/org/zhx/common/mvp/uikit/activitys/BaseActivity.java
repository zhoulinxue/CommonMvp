package org.zhx.common.mvp.uikit.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import org.zhx.common.commonnetwork.commonokhttp.customObservable.api.CommonNetRequest;
import org.zhx.common.mvp.uikit.R;
import org.zhx.common.mvp.uikit.impl.AlphaTitleProxy;
import org.zhx.common.mvp.uikit.refresh.api.OnLoadMoreListener;
import org.zhx.common.mvp.uikit.refresh.api.OnRefreshListener;
import org.zhx.common.mvp.uikit.refresh.impl.SwipeToLoadLayout;
import org.zhx.common.mvp.widgets.BaseMvpView;
import org.zhx.common.mvp.widgets.DialogApi;
import org.zhx.common.mvp.api.SimpleImmersionOwner;
import org.zhx.common.mvp.api.ViewCreatApi;
import org.zhx.common.mvp.uikit.widgets.LoadingDialog;
import org.zhx.common.mvp.utils.AppUtils;
import org.zhx.common.mvp.utils.InputMethodUtils;

import java.util.List;


/**
 * Copyright (C), 2015-2020
 * FileName: BaseActivity
 * Author: zx
 * Date: 2020/1/22 10:34
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView, OnRefreshListener, OnLoadMoreListener, ViewCreatApi<Intent>, SimpleImmersionOwner {
    private DialogApi mLoading;
    private ViewGroup mContentContainer;
    private ViewGroup mTitleContainer;
    private View mTitleDivider;
    protected AlphaTitleProxy mTitleProxy;
    protected boolean isKeyBoardShowing = false;
    protected InputMethodUtils lookKeyBoard;
    private ImageView mBgImg;
    protected Handler mHandler;
    private View mRootView;
    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && AppUtils.isTranslucentOrFloating(this)) {
            //8.0 版本 闪退问题 透明主题
            boolean result = AppUtils.fixOrientation(this);
        }
        super.onCreate(savedInstanceState);
        initkeyBoard();
        Log.i("Activitys-->", this.getClass().getSimpleName());
        mHandler = new Handler();
        int layout = initLayout();
        if (layout != 0) {
            View view = getLayoutInflater().inflate(layout, null);
            setContentView(view);
        }
        if (getIntent() != null) {
            onLoadArgumentsData(getIntent());
        }
        onCreatView();
        if (savedInstanceState != null) {
            onLoadDataFromSavedInstanceState(savedInstanceState);
        }
        onLoadContent();
    }

    protected void initkeyBoard() {
        lookKeyBoard = new InputMethodUtils();
        lookKeyBoard.lookKeyBoard(this, new InputMethodUtils.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Log.e("lookKeyBoard", height + "__true");
                isKeyBoardShowing = true;
            }

            @Override
            public void keyBoardHide(int height) {
                Log.e("lookKeyBoard", height + "__false");
                isKeyBoardShowing = false;
            }
        });
    }


    @Override
    public final void setContentView(View view) {
        if (isLoadMore()) {
            setContentView(R.layout.layout_loadmore_layout);
            swipeToLoadLayout = findViewById(R.id.swipe_refresh_layout);
            swipeToLoadLayout.setOnLoadMoreListener(this);
            swipeToLoadLayout.setOnRefreshListener(this);
        } else if (isRefresh() && !isLoadMore()) {
            setContentView(R.layout.layout_refresh);
            swipeToLoadLayout = findViewById(R.id.swipe_refresh_layout);
            swipeToLoadLayout.setOnRefreshListener(this);
        } else {
            setContentView(R.layout.layout_nomal);
        }
        initTitleView();
        if (immersionBarEnabled()) {
            initImmersionBar();
        }
        if (view != null) {
            mContentContainer.addView(view);
        }
    }

    /**
     * @param
     * @return
     * @method
     * @description 是否 开启下拉刷新、加载更多
     * @date: 2020/6/13 10:17
     * @author: zhouxue
     */
    protected boolean isLoadMore() {
        return false;
    }

    /**
     * @param
     * @return
     * @method
     * @description 是否 支持刷新
     * @date: 2020/6/13 10:16
     * @author: zhouxue
     */
    protected boolean isRefresh() {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    /**
     * @param
     * @return
     * @method
     * @description 下拉刷下结束
     * @date: 2020/6/13 10:39
     * @author: zhouxue
     */
    protected void finishRefresh() {
        if (swipeToLoadLayout != null) {
            swipeToLoadLayout.setRefreshing(false);
        }
    }

    /**
     * @param
     * @return
     * @method
     * @description 加载更多结束
     * @date: 2020/6/13 10:39
     * @author: zhouxue
     */
    protected void finishLoadMore() {
        if (swipeToLoadLayout != null) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }

    protected void noMoreData() {
        if (swipeToLoadLayout != null) {
            swipeToLoadLayout.setLoadMoreEnabled(false);
        }
    }

    protected void initTitleView() {
        mContentContainer = findViewById(R.id.content_container);
        mTitleContainer = findViewById(R.id.title_container);
        mTitleDivider = findViewById(R.id.title_divider);
        mTitleProxy = new AlphaTitleProxy(mContentContainer, mTitleContainer);
        mLoading = creatLoadingDialog();
        mBgImg = findViewById(R.id.bg_imagView);
        mRootView = findViewById(R.id.root_layout);
    }

    public void showTitleDivider() {
        mTitleDivider.setVisibility(View.VISIBLE);
    }

    public void setTitleDividerColor(@ColorRes int color) {
        mTitleDivider.setBackgroundColor(getResources().getColor(color));
    }


    @Override
    public void onLoadArgumentsData(Intent data) {

    }

    @Override
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onLoadArgumentsData(intent);
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
    public DialogApi creatLoadingDialog() {
        return new LoadingDialog(this, R.string.common_loading_text);
    }

    @Override
    public void initImmersionBar() {
        if (isDarkTitle() || isOldAPI()) {
            ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).keyboardEnable(true).init();
        } else {
            ImmersionBar.with(this).keyboardEnable(true).init();
        }
    }

    @Override
    public boolean isOldAPI() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    @Override
    public boolean isDarkTitle() {
        return AppUtils.isDarkMode(this);
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
            if (resId != 0) {
                mLoading.setMessage(getResources().getString(resId));
            }
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

    @Override
    public View getRootView() {
        return getWindow().getDecorView();
    }

    @Override
    public List<CommonNetRequest> getRequestList() {
        return null;
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }
}
