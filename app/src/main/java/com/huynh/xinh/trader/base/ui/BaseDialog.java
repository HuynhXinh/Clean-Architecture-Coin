package com.huynh.xinh.trader.base.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog {
    protected View rootView;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rootView = View.inflate(getContext(), getLayoutId(), null);
        ButterKnife.bind(this, rootView);

        initView();
        initData();
        initEvent();

        setWindowTransparent();

        setContentView(rootView);
    }

    protected abstract int getLayoutId();

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    private void setWindowTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

            window.setGravity(getGravity());

            super.show();

        } else {
            super.show();
        }
    }

    protected int getGravity() {
        return Gravity.NO_GRAVITY;
    }
}
