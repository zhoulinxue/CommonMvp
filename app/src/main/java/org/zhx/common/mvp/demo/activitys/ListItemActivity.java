package org.zhx.common.mvp.demo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.demo.adapters.StringAdapter;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.activitys
 * @ClassName: ListItemActivity
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 16:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 16:42
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class ListItemActivity extends BaseActivity {
    private boolean isdarkbar = true;

    @Override
    public int initLayout() {
        return R.layout.list_activity;
    }

    @Override
    public void onCreatView() {
        mTitleProxy.setCommonTitle("commonMvp");
        mTitleProxy.setContentViewBelowTitleBar(true);
        mTitleProxy.setCommonBackImageVisible(View.GONE);
        showTitleDivider();
        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> datas = new ArrayList<>();
        datas.add("activity 中 优雅的网络请求");
        datas.add("fragment 中 优雅的网络请求");
        datas.add("一句话 title栏");
        datas.add("状态栏 颜色");
        datas.add("生命周期");
        StringAdapter adapter = new StringAdapter(datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(ListItemActivity.this, TestActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ListItemActivity.this, TestFragmentActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ListItemActivity.this, TestTitleActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(ListItemActivity.this, StatusBarActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(ListItemActivity.this, LifecycleActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadContent() {

    }
}
