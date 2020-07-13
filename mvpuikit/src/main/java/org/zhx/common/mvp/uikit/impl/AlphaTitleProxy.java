package org.zhx.common.mvp.uikit.impl;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import org.zhx.common.mvp.api.AlphaTitle;
import org.zhx.common.mvp.uikit.R;
import org.zhx.common.mvp.uikit.utils.StatuBarUtil;

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
    private Context mContext;
    private View.OnClickListener mLisenter;
    private ImageView mBackImg;
    private TextView mTextOptionTv;

    public AlphaTitleProxy(ViewGroup mContentContainer, ViewGroup mTitleContainer) {
        this.mContentContainer = mContentContainer;
        this.mTitleContainer = mTitleContainer;
        mStatusHeight = StatuBarUtil.getStatusBarHeight(mContentContainer.getContext());
        mTitleHeight = StatuBarUtil.getTitlebarHeight(mContentContainer.getContext());
        mDefaultHeight = mStatusHeight + mTitleHeight;
        this.mContext = mTitleContainer.getContext();
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

    @Override
    public void setContentViewBelowTitleBar(boolean isBelow) {
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
        if (layout != 0) {
            initCoustomTitleBar(LayoutInflater.from(mContentContainer.getContext()).inflate(layout, null), height);
        }
    }

    @Override
    public void setCommonTitle(String title) {
        setCoustomTitleView(R.layout.common_title_layout);
        setTitle(title);
        setTitleClickListener(null);
    }

    @Override
    public void setTitleClickListener(View.OnClickListener clickListener) {
        this.mLisenter = clickListener;
        mBackImg = mTitleContainer.findViewById(R.id.back_img);
        mTextOptionTv = mTitleContainer.findViewById(R.id.option_tv);
        if (clickListener != null) {
            mBackImg.setOnClickListener(clickListener);
            mTextOptionTv.setOnClickListener(clickListener);
        } else {
            mBackImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).finish();
                    }
                }
            });
        }
    }

    @Override
    public void showTextOption(boolean showTextOption, String src) {
        if (mTitleContainer != null) {
            TextView option = mTitleContainer.findViewById(R.id.option_tv);
            if (option != null) {
                option.setVisibility(showTextOption ? View.VISIBLE : View.GONE);
                option.setText(src);
            }
        }
    }

    @Override
    public void setTextOptionBg(@DrawableRes int bg_save) {
        if (mTitleContainer != null) {
            TextView option = mTitleContainer.findViewById(R.id.option_tv);
            if (option != null) {
                option.setBackgroundResource(bg_save);
            }
        }
    }

    public void showTextOption(boolean showTextOption, @StringRes int src) {
        showTextOption(showTextOption, mContext.getString(src));
    }

    private void setTitle(String title) {
        TextView textView = mTitleContainer.findViewById(R.id.title_tv);
        textView.setText(title);
    }

    public void setCommonTitle(@StringRes int src) {
        setCommonTitle(mContext.getResources().getString(src));
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
        this.mContext = context;
        if (mTitleContainer != null) {
            mTitleContainer.setBackgroundColor(context.getResources().getColor(color));
        }
    }

    public void setCoustomTitleBarBackgroundSrc(@DrawableRes int src) {
        if (mTitleContainer != null) {
            mTitleContainer.setBackgroundResource(src);
        }
    }

    public int getTitleHeight() {
        return mDefaultHeight;
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
