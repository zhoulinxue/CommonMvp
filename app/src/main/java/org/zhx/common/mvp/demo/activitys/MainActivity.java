package org.zhx.common.mvp.demo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.zhx.common.mvp.activitys.MvpActivity;
import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.demo.bean.WeatherInfo;
import org.zhx.common.mvp.demo.mvp.presenters.WeatherPresenter;
import org.zhx.common.mvp.demo.mvp.models.WeatherApi;
import org.zhx.common.mvp.widgets.DialogApi;

public class MainActivity extends MvpActivity<WeatherPresenter> implements WeatherApi.view {
    private TextView mTextView;

    @Override
    public WeatherPresenter initPresenter() {
        //TODO  初始化  presenter
        return new WeatherPresenter(this);
    }

    @Override
    public int initLayout() {
        //TODO 设置布局
        return R.layout.activity_main;
    }

    @Override
    public void onLoadArgumentsData(Intent intent) {
        //TODO  获取传递过来的参数

    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {
        //TODO  从低内存 获取 参数  （如果 你 在 onSaveInstanceState(Bundle outState) 方法中保存了数据）
    }


    @Override
    public void onCreatView() {
        //TODO  初始化 组件
        mTextView = findViewById(R.id.result_tv);
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
    }

    @Override
    public void onLoadContent() {
        //TODO 在这个位置 获取 网络 数据
        mPresenter.getWeatherInfo();
    }

    @Override
    public void onWeatherInfo(WeatherInfo info) {
        //TODO 天气信息 (mPresenter.getWeatherInfo()  接口回调)
        mTextView.setText(info.toString());
    }

    @Override
    public DialogApi creatLoadingDialog() {
        return null;
    }
}
