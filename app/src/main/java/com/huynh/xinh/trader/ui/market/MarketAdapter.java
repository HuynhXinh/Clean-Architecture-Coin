package com.huynh.xinh.trader.ui.market;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class MarketAdapter extends RecyclerArrayAdapter<ItemMarketViewModel> {
    MarketAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemMarketViewHolder(parent);
    }
}
