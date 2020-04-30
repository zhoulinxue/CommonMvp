package org.zhx.common.mvp.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.zhx.common.mvp.R;
import org.zhx.common.mvp.api.AlphaTitle;
import org.zhx.common.mvp.utils.StatuBarUtil;

/**
 * pakage :com.gaea.kiki.impl
 * auther :zx
 * creatTime: 2019/3/25
 */
public class AlphaTitleProxy implements AlphaTitle {
    private ViewGroup mContentContainer;
    private ViewGroup mTitleContainer;
    private int mStatusHeight = 0;
    private int mTitleHeight = 0;
    private int mDefaultHeight = 0;

    public AlphaTitleProxy(ViewGroup mContentContainer, ViewGroup mTitleContainer) {
        this.mContentContainer = mContentContainer;
        this.mTitleContainer = mTitleContainer;
        mStatusHeight = StatuBarUtil.getStatusBarHeight(mContentContainer.getContext());
        mTitleHeight = StatuBarUtil.getTitlebarHeight(mContentContainer.getContext());
        mDefaultHeight = mStatusHeight + mTitleHeight;
    }

    @Override
    public void initCoustomTitleBar(View view, int height) {
        if (view != null) {
            mTitleContainer.removeAllViews();
            RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mTitleHeight);
            relativeLayout.topMargin = mStatusHeight;
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mTitleContainer.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height));
            mTitleContainer.addView(view, relativeLayout);
        }
    }


    public void setContentViewBelowTitleBar(Context context, boolean isBelow) {
        RelativeLayout.LayoutParams titleRela = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (isBelow) {
            titleRela.addRule(RelativeLayout.BELOW, R.id.title_container);
        }
        mContentContainer.setLayoutParams(titleRela);
    }

    @Override
    public void setCoustomTitleView(int layout) {
        setCoustomTitleView(layout, mDefaultHeight);
    }

    @Override
    public void setCoustomTitleView(int layout, int height) {
        if (height != -2) {
            mTitleHeight = height - mStatusHeight;
        } else {
            mTitleHeight = RelativeLayout.LayoutParams.WRAP_CONTENT;
        }
        if (layout != 0)
            initCoustomTitleBar(LayoutInflater.from(mContentContainer.getContext()).inflate(layout, null), height);
    }


    @Override
    public void onScroll(float alpha) {

    }

    public void onScroll(int alpha) {

    }


    @Override
    public void initCoustomTitleBar(View view) {
        initCoustomTitleBar(view, mDefaultHeight);
    }


    public void setCoustomTitleBarBackgroundColor(Context context, int color) {
        if (mTitleContainer != null) {
            mTitleContainer.setBackgroundColor(context.getResources().getColor(color));
        }
    }

    public int getTitleHeight() {
        return (mTitleContainer != null && mTitleContainer.getLayoutParams() != null) ? mTitleContainer.getLayoutParams().height : mDefaultHeight;
    }

    public void bringcontentToFirst() {
        if (mContentContainer != null) {
            mContentContainer.bringToFront();
        }
    }

    public void bringTitleToFirst() {
        if (mTitleContainer != null) {
            mTitleContainer.bringToFront();
        }
    }

    public boolean onTitleTouch(MotionEvent event) {
        if (mTitleContainer != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mTitleContainer.onTouchEvent(event);
                return event.getY() < getTitleHeight();
            }
            return mTitleContainer.onTouchEvent(event);
        }
        return false;
    }
}
