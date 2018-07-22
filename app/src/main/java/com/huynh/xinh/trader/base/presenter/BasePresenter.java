package com.huynh.xinh.trader.base.presenter;


import com.huynh.xinh.domain.interactor.UseCase;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class BasePresenter<T extends Contract.IView> implements Contract.IPresenter<T> {

    private final List<UseCase> useCases;

    private WeakReference<T> view;

    public BasePresenter() {
        this.useCases = Collections.emptyList();
    }

    public BasePresenter(T view, UseCase... useCases) {
        setView(view);
        this.useCases = Arrays.asList(useCases);
    }

    public BasePresenter(UseCase... useCases) {
        this.useCases = Arrays.asList(useCases);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
        for (UseCase useCase : useCases) {
            useCase.dispose();
        }
        view = null;
    }

    protected T getView() {
        return view.get();
    }

    @Override
    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    protected boolean isViewNull() {
        return view == null || view.get() == null;
    }
}
