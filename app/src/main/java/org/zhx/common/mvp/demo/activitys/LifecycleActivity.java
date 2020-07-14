package org.zhx.common.mvp.demo.activitys;

import android.os.Bundle;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.activitys
 * @ClassName: LifecycleActivity
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 18:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 18:02
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class LifecycleActivity extends BaseActivity {
    /**
     * 使用  commonMvp  查看  重置后的生命 周期   不必再 使用 actvity 原生的 onCreate(Bundle savedInstanceState) 方法
     * 执行顺序为  初始化 presenter
     * initPresenter()..
     *
     * 状态栏 相关
     * immersionBarEnabled()..
     * initImmersionBar()..
     *
     * 获取传递 的数据
     * onLoadArgumentsData(Intent intent)..
     *
     * 初始化 view
     *  onCreatView()..
     *
     *  设置数据  从网络 获取数据
     *  onLoadContent()..
     *
     */
    @Override
    public int initLayout() {
        return R.layout.test_title_layout;
    }

    @Override
    public void onCreatView() {
        mTitleProxy.setCommonTitle("生命周期");
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadContent() {

    }
}
