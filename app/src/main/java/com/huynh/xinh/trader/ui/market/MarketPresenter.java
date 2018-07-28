package com.huynh.xinh.trader.ui.market;

import com.annimon.stream.Stream;
import com.huynh.xinh.data.utils.DateTimeUtils;
import com.huynh.xinh.domain.interactor.OutputObserver;
import com.huynh.xinh.domain.interactor.market.GetMarketParam;
import com.huynh.xinh.domain.interactor.market.GetSummaryMarkets;
import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.trader.base.presenter.BasePresenter;
import com.huynh.xinh.trader.ui.ExchangeManager;
import com.huynh.xinh.trader.ui.detail.DetailPairParam;
import com.huynh.xinh.trader.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

public class MarketPresenter extends BasePresenter<MarketContract.View> implements MarketContract.Presenter {
    private final MarketPresenterModel marketPresenterModel;
    private final GetSummaryMarkets getMarketSummary;

    @Inject
    MarketPresenter(MarketContract.View view, GetSummaryMarkets getMarketSummary) {
        super(view, getMarketSummary);
        this.marketPresenterModel = new MarketPresenterModel();
        this.getMarketSummary = getMarketSummary;
    }

    @Override
    public void loadExchanges() {
        getView().showLoading();
        Exchange exchange = Stream.of(ExchangeManager.getInstance().getExchanges())
                .filter(value -> "bitfinex".equalsIgnoreCase(value.getSymbol()))
                .findSingle()
                .orElse(ExchangeManager.getInstance().getExchanges().get(0));

        marketPresenterModel.setExchange(exchange);

        getView().onShowExchange(exchange);
    }

    @Override
    public void refresh() {
        marketPresenterModel.setPage(0);
        loadMarkets();
    }

    @Override
    public void loadMore() {
        int newPage = marketPresenterModel.getPage() + 1;
        marketPresenterModel.setPage(newPage);
        loadMarkets();
    }

    @Override
    public void onItemClick(ItemMarketViewModel marketViewModel) {
        DetailPairParam detailPairParam = MarketViewModelMapper.INSTANCE.toDetailPairParam(marketViewModel);
        Exchange exchange = marketPresenterModel.getExchange();
        detailPairParam.setMarketName(exchange.getName());
        detailPairParam.setMarketSymbol(exchange.getSymbol());

        getView().startDetailPairActivity(detailPairParam);
    }

    private void loadMarkets() {
        String exchangeName = marketPresenterModel.getExchange().getSymbol();
        int page = marketPresenterModel.getPage();

        GetMarketParam getMarketParam = GetMarketParam.builder()
                .exchangeName(exchangeName)
                .page(page)
                .after(DateTimeUtils.getLocalUnixTimestampOhlcAfter())
                .periods(getPeriods())
                .build();

        getMarketSummary.execute(new OutputObserver<List<MarketSummary>>() {
            @Override
            public void onNext(List<MarketSummary> marketSummaries) {
                super.onNext(marketSummaries);

                List<ItemMarketViewModel> marketViewModels = MarketViewModelMapper.INSTANCE.toMarketViewModels(marketSummaries);

                if (isRefresh()) {
                    if (!CommonUtils.isListEmpty(marketSummaries)) {
                        getView().render(marketViewModels);
                    } else {
                        getView().showNoData();
                    }
                } else {
                    if (!CommonUtils.isListEmpty(marketSummaries)) {
                        getView().renderMore(marketViewModels);
                    } else {
                        getView().hideLoadMore();
                    }
                }
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
                if (isRefresh()) {
                    getView().showNoData();
                } else {
                    getView().showErrorLoadMore();
                }
            }
        }, getMarketParam);
    }

    private boolean isRefresh() {
        return marketPresenterModel.getPage() == 0;
    }

    private String getPeriods() {
        return String.valueOf(EnumPeriod.ONE_HOUR.getValue());
    }
}
