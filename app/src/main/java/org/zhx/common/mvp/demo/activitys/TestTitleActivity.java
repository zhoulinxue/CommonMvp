package org.zhx.common.mvp.demo.activitys;

import android.os.Bundle;
import android.view.View;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.activitys
 * @ClassName: TestTitleActivity
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 17:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 17:19
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class TestTitleActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.test_title_layout;
    }

    @Override
    public void onCreatView() {
        //
        //-----------------使用框架 自帶的 简单title栏---------------------------------
        mTitleProxy.setCommonTitle("优雅title栏");
        //隐藏返回图标 显示 隐藏
        mTitleProxy.setCommonBackImageVisible(View.GONE);
        // 自定义返回图片
//        mTitleProxy.setBackImageRes(R.mipmap.ic_done);
        //--------------------------------------------------

        //自定义 title栏布局
//        mTitleProxy.setCoustomTitleView(R.layout.common_title_layout);
        //显示 title栏 与 内容 的 分割线
        showTitleDivider();
        //设置内容 在 title 栏下方  默认 为 title 和 content 是重叠的
        mTitleProxy.setContentViewBelowTitleBar(true);
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadContent() {

    }
}
