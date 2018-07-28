package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.interactor.OutputObserver;
import com.huynh.xinh.domain.interactor.detialpair.GetDetailPair;
import com.huynh.xinh.domain.interactor.detialpair.GetDetailPairParam;
import com.huynh.xinh.domain.models.Summary;
import com.huynh.xinh.trader.base.presenter.BasePresenter;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

public class DetailPairPresenter extends BasePresenter<DetailPairContract.View> implements DetailPairContract.Presenter {
    private final GetDetailPair getDetailPair;
    @Getter
    @Setter
    private DetailPairParam detailPairPresenterModel;

    @Inject
    DetailPairPresenter(DetailPairContract.View view, GetDetailPair getDetailPair) {
        super(view, getDetailPair);
        this.getDetailPair = getDetailPair;
    }

    @Override
    public void getDetailPair(String market, String pair) {
        getView().showLoading();
        GetDetailPairParam detailPairParam = GetDetailPairParam.builder()
                .marketName(market)
                .pair(pair)
                .build();

        getDetailPair.execute(new DetailPairResult(), detailPairParam);
    }

    private class DetailPairResult extends OutputObserver<Summary> {
        @Override
        public void onNext(Summary summary) {
            super.onNext(summary);

            getView().showDetailPair(DetailPairViewModelMapper.INSTANCE.toDetailPairViewModel(summary));
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);

            getView().showError();
        }
    }
}
