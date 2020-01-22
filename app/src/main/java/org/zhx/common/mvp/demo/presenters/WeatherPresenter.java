package org.zhx.common.mvp.demo.presenters;
import org.zhx.common.mvp.BasePresenter;
import org.zhx.common.mvp.ObjectNetRequstAdapter;
import org.zhx.common.mvp.demo.WeatherInfo;
import org.zhx.common.mvp.demo.views.WeatherApi;

/**
 * Copyright (C), 2015-2020
 * FileName: WeatherPresenter
 * Author: zx
 * Date: 2020/1/22 15:52
 * Description:
 */
public class WeatherPresenter extends BasePresenter<WeatherApi.view> implements WeatherApi.presenter {
    public WeatherPresenter(WeatherApi.view view) {
        super(view);
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
