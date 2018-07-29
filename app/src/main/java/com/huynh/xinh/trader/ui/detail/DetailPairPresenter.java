package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.interactor.OutputObserver;
import com.huynh.xinh.domain.interactor.detialpair.GetDetailPair;
import com.huynh.xinh.domain.interactor.detialpair.GetDetailPairParam;
import com.huynh.xinh.domain.interactor.detialpair.GetPeriod;
import com.huynh.xinh.domain.interactor.detialpair.GetPeriodParam;
import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.domain.models.Summary;
import com.huynh.xinh.trader.base.presenter.BasePresenter;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;
import com.huynh.xinh.trader.ui.detail.model.DetailPairPresenterModel;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static com.huynh.xinh.domain.models.EnumPeriod._12_H;
import static com.huynh.xinh.domain.models.EnumPeriod._15_M;
import static com.huynh.xinh.domain.models.EnumPeriod._1_D;
import static com.huynh.xinh.domain.models.EnumPeriod._1_H;
import static com.huynh.xinh.domain.models.EnumPeriod._1_M;
import static com.huynh.xinh.domain.models.EnumPeriod._1_W;
import static com.huynh.xinh.domain.models.EnumPeriod._2_H;
import static com.huynh.xinh.domain.models.EnumPeriod._30_M;
import static com.huynh.xinh.domain.models.EnumPeriod._3_D;
import static com.huynh.xinh.domain.models.EnumPeriod._3_M;
import static com.huynh.xinh.domain.models.EnumPeriod._4_H;
import static com.huynh.xinh.domain.models.EnumPeriod._5_M;
import static com.huynh.xinh.domain.models.EnumPeriod._6_H;

public class DetailPairPresenter extends BasePresenter<DetailPairContract.View> implements DetailPairContract.Presenter {
    private final GetDetailPair getDetailPair;
    private final GetPeriod getPeriod;

    private final DetailPairPresenterModel detailPairPresenterModel;

    @Inject
    public DetailPairPresenter(DetailPairContract.View view,
                               GetDetailPair getDetailPair,
                               GetPeriod getPeriod) {
        super(view, getDetailPair, getPeriod);
        this.getDetailPair = getDetailPair;
        this.getPeriod = getPeriod;
        this.detailPairPresenterModel = new DetailPairPresenterModel();
    }

    @Override
    public void initData(DetailPairFragmentParam detailPairParam) {
        DetailPairMapper.INSTANCE.toDetailPairPresenterModel(detailPairParam, detailPairPresenterModel);
    }

    @Override
    public void showTitle() {
        String asset = detailPairPresenterModel.getAsset();
        String quote = detailPairPresenterModel.getQuote();
        getView().onShowTitle(asset, quote);
    }

    @Override
    public void getDetailPair() {
        getView().showLoading();
        GetDetailPairParam detailPairParam = GetDetailPairParam.builder()
                .marketName(detailPairPresenterModel.getMarketSymbol())
                .pair(detailPairPresenterModel.getPair())
                .build();

        getDetailPair.execute(new DetailPairResult(), detailPairParam);
    }

    @Override
    public void firstSelectedTabPeriod() {
        getView().onFirstSelectedTabPeriod();
    }

    @Override
    public void initTabPeriodTimeView() {
        List<EnumPeriod> periodTimeEnums = Arrays.asList(_1_M, _3_M, _5_M, _15_M, _30_M, _1_H, _2_H, _4_H, _6_H, _12_H, _1_D, _3_D, _1_W);
        getView().onInitTabPeriodTime(DetailPairMapper.INSTANCE.toItemTabPeriodTimeViewModels(periodTimeEnums));
    }

    @Override
    public void getPeriod(EnumPeriod enumPeriod, long periodAfter, long period) {
        GetPeriodParam param = GetPeriodParam.builder()
                .periodEnum(enumPeriod)
                .marketName(detailPairPresenterModel.getMarketSymbol())
                .pair(detailPairPresenterModel.getPair())
                .after(periodAfter)
                .periods(String.valueOf(period))
                .build();
        getPeriod.execute(new GetPeriodResult(), param);
    }

    private class DetailPairResult extends OutputObserver<Summary> {
        @Override
        public void onNext(Summary summary) {
            super.onNext(summary);

            getView().showDetailPair(DetailPairMapper.INSTANCE.toDetailPairViewModel(summary));
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);

            getView().showError();
        }
    }

    private class GetPeriodResult extends OutputObserver<List<Period>> {
        @Override
        public void onNext(List<Period> periods) {
            super.onNext(periods);

            getView().showChartPeriod(periods);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);

            getView().showError();
        }
    }
}
