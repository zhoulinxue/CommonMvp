package org.zhx.common.mvp.demo.activitys;

import android.os.Bundle;
import android.widget.TextView;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.activitys
 * @ClassName: StatusBarActivity
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 17:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 17:48
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class StatusBarActivity extends BaseActivity {
    private TextView mContent;

    /**
     * 是否开启 沉浸式状态栏 默认是开启
     *
     * @return
     */
    @Override
    public boolean immersionBarEnabled() {
        return super.immersionBarEnabled();
    }

    /**
     * 状态栏文字颜色仅仅支持  黑色 和白色
     *
     * @return
     */
    @Override
    public boolean openDarkStatuBar() {
        // false 白色状态栏   默认 为true
        // true 是 黑色状态栏文字
        return super.openDarkStatuBar();
    }

    @Override
    public int initLayout() {
        return R.layout.test_title_layout;
    }

    @Override
    public void onCreatView() {
        mContent=findViewById(R.id.content_tv);
        mContent.setText("改变 openDarkStatuBar（）方法 返回值 调整 状态栏文字颜色  为黑色或是 白色");
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadContent() {

    }
}
