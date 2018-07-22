package com.huynh.xinh.trader.ui.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XinhHuynh on 2/27/2018.
 */

@Module
public class MainActivityModule {
    @Provides
    MainContract.Presenter provideMainPresenter(MainContract.View mainView){
        return new MainPresenter(mainView);
    }

    @Provides
    MainContract.View provideMainView(MainActivity mainActivity){
        return mainActivity;
    }

}
