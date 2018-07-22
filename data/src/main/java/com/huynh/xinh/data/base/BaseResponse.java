package com.huynh.xinh.data.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    @SerializedName("result")
    protected T data;

    @SerializedName("allowance")
    protected Allowance allowance;

    public T getData() {
        return data;
    }

    public Allowance getAllowance() {
        return allowance;
    }

    public static class Allowance {
        private double cost;
        private double remaining;
    }

    public boolean isSuccess(){
        return getCode() == 200;
    }
}
