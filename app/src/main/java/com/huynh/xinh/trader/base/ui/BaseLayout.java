package com.huynh.xinh.trader.base.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;

public abstract class BaseLayout<T> extends RelativeLayout {

    protected BaseLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(getContentView(), this);
        ButterKnife.bind(this, view);
        setUpView();
        obtainAttributeValues(context, attrs);
    }

    protected abstract void obtainAttributeValues(Context context, AttributeSet attrs);

    protected abstract int getContentView();

    protected abstract void setUpView();

    public void setData(T data){

    }
}
