package org.zhx.common.mvp.api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * pakage :com.gaea.kiki.mvp
 * auther :zx
 * creatTime: 2019/6/28
 */
public interface ViewCreatApi<T> {
    /**
     * 初始化布局
     *
     * @return
     */
    public int initLayout();

    /**
     * 布局已设置完
     */
    public void onCreatView();

    /**
     * 获取传递参数
     *
     * @param data
     */
    public void onLoadArgumentsData(T data);

    /**
     * 从 savedInstanceState 中获取 数据
     *
     * @param savedInstanceState
     */
    public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState);

    /**
     * 加载 网络数据
     */
    public void onLoadContent();

    /**
     * @return
     */
    public View getRootView();

    /**
     * 底部是否有输入框
     * @return
     */

    public  boolean isBottomInput() ;
}
