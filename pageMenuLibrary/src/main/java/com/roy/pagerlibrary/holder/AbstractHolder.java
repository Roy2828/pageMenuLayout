package com.roy.pagerlibrary.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbstractHolder<T> extends RecyclerView.ViewHolder {

    public AbstractHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    protected abstract void initView(View itemView);

    public abstract void bindView(RecyclerView.ViewHolder holder,T data,int pos);
}