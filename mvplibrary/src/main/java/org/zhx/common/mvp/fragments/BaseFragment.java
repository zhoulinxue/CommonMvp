package org.zhx.common.mvp.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.zhx.common.mvp.R;
import org.zhx.common.mvp.api.ViewCreatApi;
import org.zhx.common.mvp.impl.AlphaTitleProxy;
import org.zhx.common.mvp.widgets.BaseMvpView;
import org.zhx.common.mvp.widgets.DialogApi;
import org.zhx.common.mvp.widgets.LoadingDialog;

import java.util.Objects;

/**
 * @author LongpingZou
 * @date 2017/11/13
 */

public abstract class BaseFragment extends Fragment implements BaseMvpView, ViewCreatApi<Bundle>{

    protected DialogApi progressDialog;
    private RelativeLayout rootView;
    private ViewGroup mContentContainer;
    private ViewGroup mTitleContainer;
    protected AlphaTitleProxy mTitleProxy;
    protected Handler mHandler;
    protected Context mContext;

    public Activity getParentContext() {
        return Objects.requireNonNull(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("CurrentPage-->", this.getClass().getSimpleName());
        mHandler = new Handler();
        rootView = (RelativeLayout) inflater.inflate(R.layout.immersion_bar_layout, null);
        mContentContainer = rootView.findViewById(R.id.content_container);
        mTitleContainer = rootView.findViewById(R.id.title_container);
        mTitleProxy = new AlphaTitleProxy(mContentContainer, mTitleContainer);
        int layout = initLayout();
        if (layout != 0) {
            inflater.inflate(layout, mContentContainer);
        }
        onCreatView();
        if (getArguments() != null)
            onLoadArgumentsData(getArguments());
        if (savedInstanceState != null) {
            onLoadDataFromSavedInstanceState(savedInstanceState);
        }
        onLoadContent();
        return rootView;
    }

    @Override
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLoadArgumentsData(@Nullable Bundle data) {

    }

    @Override
    public final void onCreatView() {
        onCreateView(getRootView());
    }

    protected abstract void onCreateView(View rootView);

    protected void showLoading(int resID) {
        if (!getParentContext().isFinishing()) {
            if (progressDialog != null) {
                progressDialog.dismissloading();
            }
            progressDialog = new LoadingDialog(getParentContext(), getResources().getString(resID));
            try {
                ((LoadingDialog) progressDialog).show();
            } catch (Exception ignored) {

            }

        }
    }

    protected void dismissLoading() {
        if (progressDialog != null) {
            new Handler(getParentContext().getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    //execute the task
                    if (progressDialog != null) {
                        progressDialog.dismissloading();
                    }

                }
            }, 0);

        }
    }


    public void showToast(final String message) {
        if (!TextUtils.isEmpty(message)) {
            if (getActivity() != null && !getActivity().isFinishing()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void showToast(int res) {
        if (getActivity() != null && !getActivity().isFinishing())
            Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 显示输入框
     *
     * @param editText
     */
    protected void showKeyBoard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    protected void hideKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(R.string.loading_default_text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public DialogApi creatLoadingDialog() {
        return new LoadingDialog(mContext, getResources()
                .getString(R.string.loading_default_text));
    }

    @Override
    public void showLoadingDialog(int resId) {
        try {
            if (progressDialog == null) {
                progressDialog = new LoadingDialog(mContext);
            }
            progressDialog.setMessage(getResources().getString(resId));
            if (!progressDialog.isShowing())
                progressDialog.showLoading();

        } catch (Exception ignored) {

        }
    }

    @Override
    public void onError(String code, String msg) {
        showToast(msg);
    }

    @Override
    public void dismissLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismissloading();
        }
    }

    @Override
    public View getRootView() {
        return rootView;
    }
}
