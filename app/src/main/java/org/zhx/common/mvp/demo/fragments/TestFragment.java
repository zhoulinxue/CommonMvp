package org.zhx.common.mvp.demo.fragments;

import android.view.View;
import android.widget.TextView;

import org.zhx.common.mvp.demo.R;
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
    private TextView mTextView;

    @Override
    public WeatherPresenter initPresenter() {
        //TODO 初始化 天气 presenter
        return new WeatherPresenter(this);
    }

    @Override
    protected void onCreateView(View rootView) {
        //TODO 初始化 view findViewById
        mTextView = rootView.findViewById(R.id.result_tv);
    }

    @Override
    public int initLayout() {
        //TODO 初始化 布局文件
        return R.layout.activity_main;
    }

    @Override
    public void onLoadContent() {
        //TODO 加载网络数据 或者 设置 传递过来的参数
        mPresenter.getWeatherInfo();
    }

    @Override
    public void onWeatherInfo(WeatherInfo info) {
        //TODO 天气信息 (mPresenter.getWeatherInfo()  接口回调)
        mTextView.setText(info.toString());
    }
}
