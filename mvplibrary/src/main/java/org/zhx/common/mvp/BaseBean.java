package org.zhx.common.mvp;

import org.zhx.common.commonnetwork.commonokhttp.customObservable.api.BaseData;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseBean
 * Author: zx
 * Date: 2020/1/23 9:29
 * Description:
 */
public class BaseBean<T> implements BaseData<T> {
    private String code;

    private String message;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean isSuc() {
        return "200".equals(code)  || "1".equals(1);
    }

    @Override
    public String responeCode() {
        return getCode()+"";
    }

    @Override
    public T resultData() {
        return getData();
    }

    @Override
    public String message() {
        return getMessage();
    }
}
