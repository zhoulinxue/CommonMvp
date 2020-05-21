package org.zhx.common.mvp;

import android.text.TextUtils;
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
    protected List<CommonNetRequest> mRequests = new ArrayList<>();

    public BasePresenter(V view) {
        this.mView = view;
        creatNewHttpManager();
    }

    /**
     * @return
     */
    private void creatNewHttpManager() {
        manager = HttpManager.getInstance();
        OkConfig config = onCreatHttpCofig();
        manager.initFactoryByTag(config);
    }

    /**
     * 可根据 presenter  配置 适合 的 retrofit builder
     *
     * @return
     */
    protected OkConfig onCreatHttpCofig() {
        OkConfig config =creatNewConfigByTag(HttpManager.DEFAULT_TAG);
        return config;
    }

    protected OkConfig creatNewConfigByTag(String tag) {
        OkConfig config = new OkConfigBuilder(tag)
                .setCallFactory(CommonCallAdapterFactory.create())
                .setConverterFactory(FastJsonConverterFactory.create())
                .setHttps(true)
                .build();
        if(HttpManager.DEFAULT_TAG.equals(tag)){
            config.setOkInterceptor(creatHeaderIntercepor());
        }else {
            config.setOkInterceptor(creatHeaderIntercepor(tag));
        }
        return config;
    }

    protected Interceptor creatHeaderIntercepor() {
        return null;
    }
    protected Interceptor creatHeaderIntercepor(String tag) {
        return null;
    }


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
     * 字符串数组 转 map
     *
     * @param params
     * @return
     */
    protected Map<String, Object> genrateMap(Object... params) {
        Map<String, Object> map = new HashMap<>();
        if (params.length >= 2) {
            if (params.length % 2 == 0) {
                for (int i = 0; i < params.length; i++) {
                    if (i % 2 == 0) {
                        if (!"null".equals(params[i + 1])&&params[i+1]!=null)
                            map.put(params[i]+"", params[i + 1]);
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

    public List<CommonNetRequest> getRequests() {
        return mRequests;
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

    public boolean hasMore() {
        return hasMore;
    }
}
