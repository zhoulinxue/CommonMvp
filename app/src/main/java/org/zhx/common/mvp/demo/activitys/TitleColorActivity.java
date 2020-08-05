package org.zhx.common.mvp.demo.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.zhx.common.mvp.demo.R;
import org.zhx.common.mvp.uikit.activitys.BaseActivity;

public class TitleColorActivity extends BaseActivity {
    public static void skip(Activity context) {
        context.startActivity(new Intent(context, TitleColorActivity.class));
        context.overridePendingTransition(R.anim.bottom_slid_in, R.anim.bottom_slid_out);
    }

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
                mTitleProxy.setCoustomTitleBarBackgroundColor(TitleColorActivity.this, index % 2 == 0 ? R.color.colorPrimary : R.color.colorAccent);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_slid_in, R.anim.bottom_slid_out);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
