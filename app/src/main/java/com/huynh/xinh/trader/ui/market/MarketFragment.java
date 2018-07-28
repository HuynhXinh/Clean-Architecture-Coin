package com.huynh.xinh.trader.ui.market;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseFragment;
import com.huynh.xinh.trader.ui.detail.DetailPairActivity;
import com.huynh.xinh.trader.ui.detail.DetailPairParam;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MarketFragment extends BaseFragment implements MarketContract.View,
        RecyclerArrayAdapter.OnMoreListener,
        RecyclerArrayAdapter.OnErrorListener,
        SwipeRefreshLayout.OnRefreshListener,
        RecyclerArrayAdapter.OnItemClickListener {

    public static final String TAG = "MarketFragment";

    @BindView(R.id.recycler_view_all)
    EasyRecyclerView recyclerView;

    private MarketAdapter adapter;

    @Inject
    MarketContract.Presenter presenter;

    public static MarketFragment newInstance() {

        Bundle args = new Bundle();

        MarketFragment fragment = new MarketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initView(View view) {
        adapter = new MarketAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        SpaceDecoration itemDecoration = new SpaceDecoration(getResources().getDimensionPixelOffset(R.dimen._10sdp));
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        adapter.setMore(R.layout.view_load_more_all, this);
        adapter.setError(R.layout.view_error_all, this);
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter.loadExchanges();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onMoreShow() {
        presenter.loadMore();
    }

    @Override
    public void onMoreClick() {

    }

    @Override
    public void onErrorShow() {

    }

    @Override
    public void onErrorClick() {

    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void onItemClick(int position) {
        presenter.onItemClick(adapter.getAllData().get(position));
    }

    @Override
    public void onShowExchange(Exchange exchange) {
        presenter.refresh();
    }

    @Override
    public void showLoading() {
        recyclerView.showProgress();
    }

    @Override
    public void render(List<ItemMarketViewModel> marketViewModels) {
        adapter.clear();
        adapter.addAll(marketViewModels);
    }

    @Override
    public void renderMore(List<ItemMarketViewModel> marketViewModels) {
        adapter.addAll(marketViewModels);
    }

    @Override
    public void showNoData() {
        recyclerView.showEmpty();
    }

    @Override
    public void showErrorLoadMore() {

    }

    @Override
    public void hideLoadMore() {
        adapter.stopMore();
    }

    @Override
    public void startDetailPairActivity(DetailPairParam detailPairParam) {
        DetailPairActivity.start(getActivity(), detailPairParam);
    }
}
