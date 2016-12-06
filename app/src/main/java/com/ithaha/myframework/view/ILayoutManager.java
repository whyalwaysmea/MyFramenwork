package com.ithaha.myframework.view;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Long
 * on 2016/6/17.
 */
public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int findLastVisiblePosition();
    void setUpAdapter(BaseListAdapter adapter);
}
