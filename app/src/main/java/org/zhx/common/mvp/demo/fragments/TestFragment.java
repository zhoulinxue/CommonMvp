package org.zhx.common.mvp.demo.fragments;

import android.view.View;

import org.zhx.common.mvp.demo.bean.WeatherInfo;
import org.zhx.common.mvp.demo.mvp.models.WeatherApi;
import org.zhx.common.mvp.demo.mvp.presenters.WeatherPresenter;
import org.zhx.common.mvp.uikit.fragments.MvpFragment;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.fragments
 * @ClassName: TestFragment
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 16:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 16:25
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class TestFragment extends MvpFragment<WeatherPresenter> implements WeatherApi.view {
    @Override
    public WeatherPresenter initPresenter() {
        //初始化 天气 presenter
        return new WeatherPresenter(this);
    }

    @Override
    protected void onCreateView(View rootView) {
    // 初始化 view findViewById
    }

    @Override
    public int initLayout() {
        return 0;
    }

    @Override
    public void onLoadContent() {

    }

    @Override
    public void onWeatherInfo(WeatherInfo info) {

    }

    @Override
    public boolean openDarkStatuBar() {
        return false;
    }
}
