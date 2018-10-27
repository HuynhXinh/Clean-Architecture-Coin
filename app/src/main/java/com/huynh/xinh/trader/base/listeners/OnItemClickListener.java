package com.huynh.xinh.trader.base.listeners;

@FunctionalInterface
public interface OnItemClickListener<D> {
    OnItemClickListener DEFAULT = (itemViewModel, position) -> {
    };

    void onItemClick(D itemViewModel, int position);
}