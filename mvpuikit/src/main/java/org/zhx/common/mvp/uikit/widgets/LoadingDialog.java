package org.zhx.common.mvp.uikit.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.zhx.common.mvp.R;
import org.zhx.common.mvp.widgets.DialogApi;


/**
 * @author LongpingZou
 * @date 2017/11/21
 */
public class LoadingDialog extends Dialog implements DialogApi {
    private TextView tvMsg;
    private String message;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, String tvMsg) {
        super(context);
        this.message = tvMsg;
        initView();
    }

    public LoadingDialog(Context context, int src) {
        super(context);
        this.message = context.getResources().getString(src);
        initView();
    }

    public LoadingDialog(Context context, int theme, String strMessage) {
        super(context, theme);
        this.message = strMessage;
        initView();
    }

    private void initView() {
        View view = getLayoutInflater().inflate(R.layout.common_layout_loading, null);
        this.setContentView(view);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tvMsg = view.findViewById(R.id.commone_loading_tv);
        if (tvMsg != null) {
            tvMsg.setText(message);
        }
        setCanceledOnTouchOutside(false);
    }

    public void setMessage(String message) {
        this.message = message;
        tvMsg.setText(message);
    }

    @Override
    public void showLoading() {
        if (!isShowing()) {
            show();
        }
    }

    @Override
    public void dismissloading() {
        if (isShowing()) {
            dismiss();
        }
    }
}
