package org.zhx.common.mvp.demo.activitys;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.demo.bean.WeatherInfo;
import org.zhx.common.mvp.demo.mvp.presenters.WeatherPresenter;
import org.zhx.common.mvp.demo.mvp.models.WeatherApi;
import org.zhx.common.mvp.uikit.activitys.MvpActivity;

public class TestActivity extends MvpActivity<WeatherPresenter> implements WeatherApi.view {
    private TextView mTextView;

    /**
     *  重置了  activity 生命周期   方法执行 顺序 依次往下
     * @return
     */
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
    public void onCreatView() {
        //TODO  初始化 组件
        mTextView = findViewById(R.id.result_tv);
    }
    @Override
    public void onLoadArgumentsData(Intent intent) {
        //TODO  获取传递过来的参数

    }
    @Override
    public void onLoadContent() {
        //TODO 获取 网络 数据
        mPresenter.getWeatherInfo();
    }

    @Override
    public void onWeatherInfo(WeatherInfo info) {
        //TODO 天气信息 (mPresenter.getWeatherInfo()  接口回调)
        mTextView.setText(info.toString());
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {
        //TODO  从低内存 获取 参数  （如果 你 在 onSaveInstanceState(Bundle outState) 方法中保存了数据）
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO 保存 数据 以供 从低内存恢复 时 还原界面
    }


    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
    }

    @Override
    public boolean openDarkStatuBar() {
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ListItemActivity.class));
        finish();
    }
}
