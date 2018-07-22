package com.huynh.xinh.trader.base.presenter;


public interface Contract {

    interface IView {

    }

    interface IPresenter<T extends Contract.IView> {
        void setView(T view);

        void onResume();

        void onPause();

        void onStop();

        void onDestroy();
    }
}
