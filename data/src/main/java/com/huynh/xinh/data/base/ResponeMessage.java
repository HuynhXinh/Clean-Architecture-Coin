package com.huynh.xinh.data.base;


import com.google.gson.annotations.SerializedName;

public class ResponeMessage {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
