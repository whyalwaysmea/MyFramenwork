package com.ithaha.myframework.base;

import android.view.ViewGroup;

import com.ithaha.myframework.R;
import com.ithaha.myframework.view.BaseListAdapter;
import com.ithaha.myframework.view.BaseViewHolder;
import com.ithaha.myframework.view.ILayoutManager;
import com.ithaha.myframework.view.MyLinearLayoutManager;
import com.ithaha.myframework.view.PullToRefreshRecyclerView;

import java.util.ArrayList;

public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshRecyclerView.OnRecyclerRefreshListener {


    protected ArrayList<T> mData = new ArrayList<>();
    public MyBaseListAdapter mBaseAdapter;
    protected PullToRefreshRecyclerView mPullToRefreshRecyclerView;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_list, R.string.list,  MODE_BACK);
    }

    @Override
    protected void initView() {
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.pullToRefreshRecyclerView);
        mPullToRefreshRecyclerView.setLayoutManager(setLayoutManager());
        mBaseAdapter = new MyBaseListAdapter();

        mPullToRefreshRecyclerView.setAdapter(mBaseAdapter);
        mPullToRefreshRecyclerView.setOnRefresh(this);
    }


    protected void initData() {
    }

    @Override
    public void onPullToRefresh() {

    }

    public ILayoutManager setLayoutManager() {
        return new MyLinearLayoutManager(this);
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
