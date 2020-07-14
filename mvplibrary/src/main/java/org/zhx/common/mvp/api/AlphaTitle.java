package org.zhx.common.mvp.api;

import android.view.View;

import androidx.annotation.DrawableRes;

/**
 * pakage :com.gaea.kiki.api
 * auther :zx
 * creatTime: 2019/3/22
 */
public interface AlphaTitle {
    public void onScroll(float alpha);

    public void initCoustomTitleBar(View view);

    public void initCoustomTitleBar(View view, int height);

    public void setContentViewBelowTitleBar(boolean isBelow);

    public void setCoustomTitleView(int layout);

    public void setCoustomTitleView(int layout, int height);

    public void setCommonTitle(String title);

    public void setCommonBackImageVisible(int visible);

    public void setTitleClickListener(View.OnClickListener clickListener);

    void showTextOption(boolean showTextOption, String text);

    void setTextOptionBg(@DrawableRes int bg_save);
}
