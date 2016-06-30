package com.ithaha.myframework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ithaha.myframework.R;
import com.ithaha.myframework.base.BaseListAdapter;
import com.ithaha.myframework.view.BaseViewHolder;
import com.ithaha.myframework.view.ILayoutManager;
import com.ithaha.myframework.view.MyLinearLayoutManager;
import com.ithaha.myframework.view.PullToRefreshRecyclerView;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/6/30.
 */
public abstract class BaseListFragment<T> extends BaseFragment implements PullToRefreshRecyclerView.OnRecyclerRefreshListener {

    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    protected MyBaseListAdapter mAdapter;
    protected ArrayList<T> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pullToRefreshRecyclerView);
        mPullToRefreshRecyclerView.setLayoutManager(setLayoutManager());
        mPullToRefreshRecyclerView.setOnRefresh(this);
        mAdapter = new MyBaseListAdapter();
        mPullToRefreshRecyclerView.setAdapter(mAdapter);

        initData();
    }

    protected abstract void initData();

    public ILayoutManager setLayoutManager() {
        return new MyLinearLayoutManager(getContext());
    }

    @Override
    public void onPullToRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    protected class MyBaseListAdapter extends BaseListAdapter {

        @Override
        public BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override
        protected int getDataCount() {
            return mData != null ? mData.size() : 0;
        }

        @Override
        public int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        public boolean isSectionHeader(int position) {
            return setSectionHeader(position);
        }
    }

    protected boolean setSectionHeader(int position) {
        return false;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    public int getItemType(int position) { return 0;}

}
