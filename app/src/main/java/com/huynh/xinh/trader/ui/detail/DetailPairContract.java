package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.trader.base.presenter.Contract;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;
import com.huynh.xinh.trader.ui.detail.model.DetailPairViewModel;
import com.huynh.xinh.trader.ui.detail.model.ItemTabPeriodTimeViewModel;
import com.huynh.xinh.trader.ui.detail.model.PriceAtPeriodTimeViewModel;

import java.util.List;

public class DetailPairContract {
    public interface View extends Contract.IView {

        void onInitTabPeriodTime(List<ItemTabPeriodTimeViewModel> itemTabPeriodTimeViewModels);

        void onShowTitle(String asset, String quote);

        void showDetailPair(DetailPairViewModel detailPairViewModel);

        void onFirstSelectedTabPeriod();

        void showChartPeriod(List<Period> periods);

        void showLoading();

        void showError();

        void showLoadChart();

        void hideLoadingChart();

        void clearChart();

        void showErrorChart();

        void showPriceAtPeriodTime(PriceAtPeriodTimeViewModel priceAtPeriodTimeViewModel);
    }

    public interface Presenter extends Contract.IPresenter<DetailPairContract.View> {

        void initData(DetailPairFragmentParam detailPairParam);

        void showTitle();

        void getDetailPair();

        void firstSelectedTabPeriod();

        void initTabPeriodTimeView();

        void onClickPeriodTime(EnumPeriod periodEnum, long periodAfter, long period);

        void showValueSelected(Period period);
    }
}
