package com.roy.pagerlibrary.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageViewPagerAdapter extends PagerAdapter {

    private final List<View> mViewList = new ArrayList<>();

    public PageViewPagerAdapter(List<View> objects) {
        if (objects != null) {
            this.mViewList.addAll(objects);
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        int idx = mViewList.indexOf(object);
        return idx == -1 ? POSITION_NONE : idx;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View item = mViewList.get(position % mViewList.size());

        final int id = generateViewIdForItem(item);

        item.setId(id);
        container.addView(item);

        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        final int id = generateViewIdForItem((View)object);
        View view = container.findViewById(id);
        if (view != null) {
            container.removeView(view);
        }
    }

    public void updateSetChange(List<View> objects) {
        this.mViewList.clear();
        if (objects != null) {
            this.mViewList.addAll(objects);
        }
        notifyDataSetChanged();
    }

    private static int generateViewIdForItem(View item) {
        return Math.abs(item.hashCode());
    }
}
