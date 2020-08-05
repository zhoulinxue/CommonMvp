package org.zhx.common.mvp.demo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.zhx.common.mvp.demo.R;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.activitys
 * @ClassName: RouteActivity
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/8/4 14:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/4 14:27
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class RouteActivity extends AppCompatActivity {
    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        if (getIntent() != null) {
            switch (getIntent().getAction()) {
                case "test":
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TitleColorActivity.skip(RouteActivity.this);
                            finish();
                        }
                    }, 300);
                    break;
            }
        }
    }
}
