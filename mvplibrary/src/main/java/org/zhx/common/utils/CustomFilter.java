package org.zhx.common.utils;

import android.text.TextUtils;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * pakage :company.yinu.android.customs.customView
 * auther :zx
 * creatTime: 2019/8/8
 * description :
 */
public class CustomFilter<T> extends Filter {
    private List<T> mList;
    private OnFilterCallback<T> callback;

    public CustomFilter(List<T> mList) {
        this.mList = mList;
    }

    public OnFilterCallback<T> getCallback() {
        return callback;
    }

    public void setCallback(OnFilterCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults result = new FilterResults();
        List<T> list = null;
        if (TextUtils.isEmpty(constraint)) {
            list = mList;
        } else {
            list = new ArrayList<>();
            if (callback != null) {
                for (T t : mList) {
                    if (callback.filter(t, constraint)) {
                        list.add(t);
                    }
                }
            }
        }
        result.values = list; //将得到的集合保存到FilterResults的value变量中
        result.count = list.size();//将集合的大小保存到FilterResults的count变量中
        return result;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults filterResults) {
        if (callback != null) {
            if (TextUtils.isEmpty(constraint)) {
                callback.onCancel((List<T>) filterResults.values);
            } else {
                callback.onFilterResult((List<T>) filterResults.values);
            }
        }
    }

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    public interface OnFilterCallback<T> {

        public boolean filter(T t, CharSequence keys);

        public void onFilterResult(List<T> results);

        void onCancel(List<T> results);
    }
}
