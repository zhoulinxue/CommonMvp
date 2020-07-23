package org.zhx.common.mvp.demo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

public class TitleColorActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.test_title_layout;
    }

    @Override
    public void onCreatView() {
        mTitleProxy.setCoustomTitleBarBackgroundColor(this, R.color.colorPrimary);
        mTitleProxy.setCommonTitle("title 颜色");
        mTitleProxy.setCommonTitleColor(R.color.white);
        mTitleProxy.setCommonBackImageVisible(View.GONE);
        final TextView textView = findViewById(R.id.content_tv);
        textView.setText("改变颜色");

        textView.setOnClickListener(new View.OnClickListener() {
            int index = 0;

            @Override
            public void onClick(View v) {
                index++;
                mTitleProxy.setCoustomTitleBarBackgroundColor(TitleColorActivity.this, index % 2 == 0 ? R.color.colorPrimary:R.color.colorAccent);
            }
        });
    }

    @Override
    public boolean openDarkStatuBar() {
        return false;
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadContent() {

    }
}
