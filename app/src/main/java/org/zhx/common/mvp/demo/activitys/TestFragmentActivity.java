package org.zhx.common.mvp.demo.activitys;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.demo.fragments.TestFragment;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.activitys
 * @ClassName: FragmentFragment
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 16:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 16:33
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class TestFragmentActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.fragment_layout;
    }

    @Override
    public void onCreatView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, new TestFragment()).commit();
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadContent() {

    }
}
