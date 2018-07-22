package com.huynh.xinh.trader.base.listeners;

@FunctionalInterface
public interface OnItemClickListener<D> {
    OnItemClickListener DEFAULT = (object, position) -> {
    };

    void onItemClick(D object, int position);
}