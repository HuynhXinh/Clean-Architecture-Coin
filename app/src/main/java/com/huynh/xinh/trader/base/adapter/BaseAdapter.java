package com.huynh.xinh.trader.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.huynh.xinh.trader.base.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter<D, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    private List<D> data = new ArrayList<>();
    private OnItemClickListener<D> onItemClickListener;

    public BaseAdapter(Context context) {
        init(context, Collections.emptyList());
    }

    public BaseAdapter(Context context, List<D> data) {
        init(context, data);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolderFactory(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected abstract VH onCreateViewHolderFactory(ViewGroup parent, int viewType);

    public void setOnItemClickListener(OnItemClickListener<D> listener) {
        this.onItemClickListener = listener;
    }

    private void init(Context context, List<D> data) {
        this.context = context;
        this.data.addAll(data);
    }

    public void setData(List<D> data) {
        this.data.clear();
        this.data.addAll(data);
    }
}