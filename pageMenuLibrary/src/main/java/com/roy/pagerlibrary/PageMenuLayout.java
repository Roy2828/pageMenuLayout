package com.roy.pagerlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.roy.pagerlibrary.adapter.EntranceAdapter;
import com.roy.pagerlibrary.adapter.PageViewPagerAdapter;
import com.roy.pagerlibrary.holder.PageMenuViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class PageMenuLayout<T> extends RelativeLayout {
    private static final int DEFAULT_ROW_COUNT = 2;
    private static final int DEFAULT_SPAN_COUNT = 5;
    private CustomViewPager mViewPager;
    /**
     * 行数
     */
    private int mRowCount = DEFAULT_ROW_COUNT;
    /**
     * 列数
     */
    private int mSpanCount = DEFAULT_SPAN_COUNT;

    public PageMenuLayout(Context context) {
        this(context, null, 0);
    }

    public PageMenuLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageMenuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mViewPager = new CustomViewPager(context);
        addView(mViewPager, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PageMenuLayout);
        if (typedArray != null) {
            mRowCount = typedArray.getInteger(R.styleable.PageMenuLayout_pagemenu_row_count, DEFAULT_ROW_COUNT);
            mSpanCount = typedArray.getInteger(R.styleable.PageMenuLayout_pagemenu_span_count, DEFAULT_SPAN_COUNT);
            typedArray.recycle();
        }

    }

    public void setPageDatas(@NonNull List<T> datas, @NonNull PageMenuViewHolderCreator creator) {
        setPageDatas(mRowCount, mSpanCount, datas, creator);
    }

    /**
     * @return 菜单总页数
     */
    public int getPageCount() {
        if (mViewPager != null && mViewPager.getAdapter() != null) {
            return mViewPager.getAdapter().getCount();
        } else {
            return 0;
        }
    }

    /**
     * 页面滚动监听
     * @param pageListener
     */
    public void setOnPageListener(ViewPager.OnPageChangeListener pageListener) {
        if (mViewPager != null) {
            mViewPager.addOnPageChangeListener(pageListener);
        }
    }

    /**
     * 设置行数
     * @param rowCount
     */
    public void setRowCount(int rowCount) {
        mRowCount = rowCount;
    }

    /**
     * 设置列数
     * @param spanCount
     */
    public void setSpanCount(int spanCount) {
        mSpanCount = spanCount;
    }
    List<View> viewList = new ArrayList<>();
    PageViewPagerAdapter adapter;
    public void setPageDatas(int rowCount, int spanCount, @NonNull List<T> datas, @NonNull PageMenuViewHolderCreator creator) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mRowCount = rowCount;
        mSpanCount = spanCount;
        if (mRowCount == 0 || mSpanCount == 0) {
            return;
        }
        int pageSize = mRowCount * mSpanCount;
        int pageCount = (int) Math.ceil(datas.size() * 1.0 / pageSize);
        viewList.clear();
        for (int index = 0; index < pageCount; index++) {
            RecyclerView recyclerView = new RecyclerView(this.getContext());
            recyclerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), mSpanCount));
            EntranceAdapter<T> entranceAdapter = new EntranceAdapter<>(creator, datas, index, pageSize);
            recyclerView.setAdapter(entranceAdapter);
            viewList.add(recyclerView);
        }
        if(adapter==null) {
            adapter = new PageViewPagerAdapter(viewList);
            mViewPager.setAdapter(adapter);
        }
        mViewPager.setCurrentItem(0);
        adapter.notifyDataSetChanged();
    }


}
