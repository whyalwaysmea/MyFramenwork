package com.ithaha.myframework.view;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by Long
 * on 2016/6/20.
 */
public class FooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private BaseListAdapter mAdapter;
    private int spanCount;

    public FooterSpanSizeLookup(BaseListAdapter adapter, int spanCount) {
        this.mAdapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if(mAdapter.isLoadMore(position) || mAdapter.isSectionHeader(position)) {
            return spanCount;
        }
        return 1;
    }
}
