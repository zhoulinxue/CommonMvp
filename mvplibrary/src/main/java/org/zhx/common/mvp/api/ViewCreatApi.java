package org.zhx.common.mvp.api;

import android.content.Intent;
import android.os.Bundle;

/**
 * pakage :com.gaea.kiki.mvp
 * auther :zx
 * creatTime: 2019/6/28
 */
public interface ViewCreatApi {
    /**
     * 初始化布局
     *
     * @return
     */
    public  int initLayout();

    /**
     * 获取传递参数
     *
     * @param intent
     */
    public  void onLoadIntentData(Intent intent);

    /**
     * 从 savedInstanceState 中获取 数据
     *
     * @param savedInstanceState
     */
    public  void onLoadDataFromSavedInstanceState(Bundle savedInstanceState);

    /**
     * 布局已设置完
     */
    public  void onCreatView();

    /**
     * 加载 网络数据
     */
    public  void onLoadContent();


}
