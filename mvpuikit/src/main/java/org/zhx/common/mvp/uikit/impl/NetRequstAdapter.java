package org.zhx.common.mvp.uikit.impl;


import android.util.Log;

import org.zhx.common.commonnetwork.CommonOkHttp;
import org.zhx.common.commonnetwork.NetWorkUtil;
import org.zhx.common.commonnetwork.api.CommonLocalError;
import org.zhx.common.commonnetwork.api.CommonNetRequestCallBack;
import org.zhx.common.mvp.uikit.CommonMvp;
import org.zhx.common.mvp.uikit.api.widgets.BaseMvpView;
import org.zhx.common.mvp.utils.CommonFileCacheUtil;

/**
 * pakage :com.gaea.kiki.mvp
 * auther :zx
 * creatTime: 2019/6/28
 */
public abstract class NetRequstAdapter<R, T> implements CommonNetRequestCallBack<R, T> {
    protected BaseMvpView mvpView;
    private boolean isShowToast = true;
    private boolean isDismissDialog = true;
    private String sharkeKey = "common_NetWork_shake";

    public NetRequstAdapter(BaseMvpView mvpView, boolean isShowToast) {
        this(mvpView);
        this.isShowToast = isShowToast;
    }

    public NetRequstAdapter(BaseMvpView mvpView, boolean isShowToast, boolean isDismissDialog) {
        this(mvpView);
        this.isShowToast = isShowToast;
        this.isDismissDialog = isDismissDialog;
    }

    public NetRequstAdapter(boolean isDismissDialog, BaseMvpView mvpView) {
        this(mvpView);
        this.isDismissDialog = isDismissDialog;
    }

    public NetRequstAdapter(BaseMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onLoadComplete() {
        if (mvpView != null && isDismissDialog) {
            mvpView.dismissLoadingDialog();
        }
    }

    @Override
    public void onError(String responseCode, String msg) {
        Log.e("OkHttpRequest", "onError..NetRequstAdapter...");
        if (CommonOkHttp.mFilter != null) {
            CommonOkHttp.mFilter.onError(responseCode, msg);
        }
        if (mvpView != null) {
            long time = CommonFileCacheUtil.shake(mvpView.getContext(), sharkeKey, 0);
            if (System.currentTimeMillis() - time > 3000) {
                synchronized (NetRequstAdapter.class) {
                    if (!NetWorkUtil.checkNetWorkStatus(mvpView.getContext())) {
                        CommonFileCacheUtil.save(mvpView.getContext(), sharkeKey, System.currentTimeMillis());
                        onShowError("-1080707", CommonLocalError.BAD_NETWORK.getErrorMsg());
                        return;
                    }
                }
                onShowError(responseCode + "", msg);
            }
        }
    }

    private void onShowError(String code, String errorMsg) {
        mvpView.onError(code, isShowToast ? errorMsg : "");
    }
}
