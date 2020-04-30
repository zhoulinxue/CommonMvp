package org.zhx.common.mvp;

import android.util.Log;

import org.zhx.common.commonnetwork.HttpManager;
import org.zhx.common.commonnetwork.OkHttpFactory;
import org.zhx.common.commonnetwork.commonokhttp.OkConfig;
import org.zhx.common.commonnetwork.commonokhttp.OkConfigBuilder;
import org.zhx.common.commonnetwork.commonokhttp.customObservable.CommonCallAdapterFactory;
import org.zhx.common.commonnetwork.commonokhttp.customObservable.api.CommonNetRequest;
import org.zhx.common.mvp.retrofit.FastJsonConverterFactory;
import org.zhx.common.mvp.widgets.BaseMvpView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * Copyright (C), 2015-2020
 * FileName: BasePresenter
 * Author: zx
 * Date: 2020/1/22 11:47
 * Description:
 */
public abstract class BasePresenter<V extends BaseMvpView> {
    protected V mView;
    protected int pageNumber = 1;
    protected int pageSize = 10;
    protected int mTotalNum;
    protected int mTotalPage = 0;
    protected boolean hasMore;
    protected HttpManager manager;
    private boolean isNewBuilder = false;
    private OkHttpFactory factory;
    protected List<CommonNetRequest> mRequests = new ArrayList<>();

    public boolean isNewBuilder() {
        return isNewBuilder;
    }

    public BasePresenter(V view) {
        this.mView = view;
        creatNewHttpManager();
    }

    /**
     * @return
     */
    private void creatNewHttpManager() {
        manager = HttpManager.getInstance();
        manager.setDefaultTag(BasePresenter.class);
        OkConfig config = onCreatHttpCofig();
        manager.initFactoryByTag(config);
        factory = creatNewFactory();
    }

    protected OkHttpFactory creatNewFactory() {
        return manager.getDefaultFactory();
    }

    /**
     * 可根据 presenter  配置 适合 的 retrofit builder
     *
     * @return
     */
    protected OkConfig onCreatHttpCofig() {
        OkConfig config = new OkConfigBuilder(BasePresenter.class)
                .setCallFactory(CommonCallAdapterFactory.create())
                .setConverterFactory(FastJsonConverterFactory.create())
                .setHttps(true)
                .build();
        if (config.getInterceptor() == null)
            config.setOkInterceptor(creatHeaderIntercepor());
        return config;
    }

    protected Interceptor creatHeaderIntercepor() {
        return null;
    }

    ;

    /**
     * 字符串数组 转 map
     *
     * @param params
     * @return
     */
    protected Map<String, String> genrateMap(String... params) {
        Map<String, String> map = new HashMap<>();
        if (params.length >= 2) {
            if (params.length % 2 == 0) {
                for (int i = 0; i < params.length; i++) {
                    if (i % 2 == 0) {
                        if (!"null".equals(params[i + 1]))
                            map.put(params[i], params[i + 1]);
                    }
                }
            } else {
                Log.e("httpManager", "ApiManager" + "param ....key...value  error");
            }
        }
        return map;
    }

    /**
     * 取消所有回调
     */
    public void onDestory() {
        for (CommonNetRequest request : mRequests) {
            request.cancel();
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return mTotalNum;
    }

    public void setTotalNum(int mTotalNum) {
        this.mTotalNum = mTotalNum;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setTotalPage(int mTotalPage) {
        this.mTotalPage = mTotalPage;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
