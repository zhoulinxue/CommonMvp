package org.zhx.common.mvp.demo.presenters;

import org.zhx.common.commonnetwork.commonokhttp.OkConfig;
import org.zhx.common.mvp.BasePresenter;
import org.zhx.common.mvp.ObjectNetRequstAdapter;
import org.zhx.common.mvp.demo.WeatherInfo;
import org.zhx.common.mvp.demo.retrofit.FastJsonConverterFactory;
import org.zhx.common.mvp.demo.views.WeatherApi;

/**
 * Copyright (C), 2015-2020
 * FileName: WeatherPresenter
 * Author: zx
 * Date: 2020/1/22 15:52
 * Description: 天气 presenter  所有的 天气相关的逻辑都在 该类 实现 并通过 WeatherApi.view  回调到 界面
 */
public class WeatherPresenter extends BasePresenter<WeatherApi.view> implements WeatherApi.presenter {
    public WeatherPresenter(WeatherApi.view view) {
        super(view);
    }

    @Override
    protected OkConfig onCreatHttpCofig() {
        OkConfig config=super.onCreatHttpCofig();
        config.setConverterFactory(FastJsonConverterFactory.create());
        return config;
    }

    @Override
    public void getWeatherInfo() {
        manager.with(WeatherApi.class).getTest().addRequest(mRequests).excute(new ObjectNetRequstAdapter<WeatherInfo>(mView) {

            @Override
            protected void onResultData(WeatherInfo info) {
                mView.onWeatherInfo(info);
            }
        });
    }
}
