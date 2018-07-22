package com.huynh.xinh.trader.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getView();

        if (view == null) {
            view = inflater.inflate(getContentView(), container, false);
            ButterKnife.bind(this, view);
        }
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
        initData(savedInstanceState);
    }

    protected abstract int getContentView();

    protected abstract void initView(View view);

    protected abstract void initEvent();

    protected abstract void initData(Bundle savedInstanceState);

}
