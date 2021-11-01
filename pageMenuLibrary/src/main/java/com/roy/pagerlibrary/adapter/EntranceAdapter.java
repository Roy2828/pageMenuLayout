package com.roy.pagerlibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roy.pagerlibrary.holder.AbstractHolder;
import com.roy.pagerlibrary.holder.PageMenuViewHolderCreator;

import java.util.List;

public class EntranceAdapter<T> extends RecyclerView.Adapter<AbstractHolder> {

    private List<T> mDatas;

    /**
     * 页数下标,从0开始(通俗讲第几页)
     */
    private int mIndex;

    /**
     * 每页显示最大条目个数
     */
    private int mPageSize;
    private PageMenuViewHolderCreator mPageMenuViewHolderCreator;

    public EntranceAdapter(PageMenuViewHolderCreator holderCreator, List<T> datas, int index, int pageSize) {
        this.mDatas = datas;
        this.mPageSize = pageSize;
        this.mIndex = index;
        this.mPageMenuViewHolderCreator = holderCreator;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() > (mIndex + 1) * mPageSize ? mPageSize : (mDatas.size() - mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }


    @NonNull
    @Override
    public AbstractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = mPageMenuViewHolderCreator.getLayoutId();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return mPageMenuViewHolderCreator.createHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractHolder holder, int position) {
        final int pos = position + mIndex * mPageSize;
        holder.bindView(holder,mDatas.get(pos),pos);
    }

}
