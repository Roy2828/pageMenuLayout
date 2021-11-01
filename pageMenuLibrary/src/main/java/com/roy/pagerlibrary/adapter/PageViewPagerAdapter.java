package com.roy.pagerlibrary.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PageViewPagerAdapter extends PagerAdapter {

    private List<View> mViewList;
    private boolean isCanLoop;

    public PageViewPagerAdapter(List<View> viewList, boolean isCanLoop) {
        this.mViewList = viewList;
        this.isCanLoop = isCanLoop;
        if (viewList == null) {
            mViewList = new ArrayList<>();
        }
    }

    public PageViewPagerAdapter(List<View> viewList) {
        this.mViewList = viewList;
        if (viewList == null) {
            mViewList = new ArrayList<>();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  container.removeView(mViewList.get( position % mViewList.size()));
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position % mViewList.size();
        View view = mViewList.get(realPosition);

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (mViewList.isEmpty()) {
            return 0;
        }
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
