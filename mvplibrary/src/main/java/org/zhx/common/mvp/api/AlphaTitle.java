package org.zhx.common.mvp.api;

import android.content.Context;
import android.view.View;

/**
 * pakage :com.gaea.kiki.api
 * auther :zx
 * creatTime: 2019/3/22
 */
public interface AlphaTitle {
    public void onScroll(float alpha);

    public void initCoustomTitleBar(View view);

    public void initCoustomTitleBar(View view, int height);

    public void setContentViewBelowTitleBar(Context context, boolean isBelow);

    public void setCoustomTitleView(int layout);

    public void setCoustomTitleView(int layout, int height);
}
