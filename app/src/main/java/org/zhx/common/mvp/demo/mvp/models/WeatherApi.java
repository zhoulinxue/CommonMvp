package org.zhx.common.mvp.demo.mvp.models;
import org.zhx.common.commonnetwork.customObservable.CommonObservable;
import org.zhx.common.mvp.demo.bean.WeatherInfo;
import org.zhx.common.mvp.uikit.api.widgets.BaseMvpView;

import retrofit2.http.GET;

/**
 * Copyright (C), 2015-2020
 * FileName: WeatherApi
 * Author: zx
 * Date: 2020/1/21 15:52
 * Description:
 */
public interface WeatherApi {
    @GET("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100")
    public CommonObservable<WeatherInfo> getTest();

    public interface view extends BaseMvpView {
        void onWeatherInfo(WeatherInfo info);
    }
    public interface presenter {
        void getWeatherInfo();
    }
}
