package com.ithaha.myframework.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.ithaha.myframework.R;

/**
 * Created by Long
 * on 2016/6/17.
 */
public class PullToRefreshRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    private Context mContext;
    private OnRecyclerRefreshListener mOnRecyclerRefreshListener;
    private static final int ACTION_IDLE = 0;
    private static final int ACTION_PULL_TO_REFRESH = 1;
    private static final int ACTION_LOAD_MORE = 2;
    private int mCurrentState = ACTION_IDLE;
    private boolean isLoadMoreEnable = true;
    private boolean isPullToRefreshEnalbe = true;
    private ILayoutManager mLayoutManager;
    private BaseListAdapter mAdapter;

    public PullToRefreshRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_recyclerview, this, true);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoadMoreEnable && mCurrentState == ACTION_IDLE && checkIfLoadMore() && mOnRecyclerRefreshListener != null) {
                    mCurrentState = ACTION_LOAD_MORE;
                    mAdapter.setLoadMore(true);
                    swipeRefreshLayout.setEnabled(false);
                    mOnRecyclerRefreshListener.onLoadMore();
                }
            }
        });
    }

    private boolean checkIfLoadMore() {
        int lastVisibleItemPosition = mLayoutManager.findLastVisiblePosition();
        int itemCount =  mLayoutManager.findLastVisiblePosition();
        return itemCount - lastVisibleItemPosition < 5;
    }

    public void setAdapter(BaseListAdapter adapter) {
        this.mAdapter = adapter;
        recyclerView.setAdapter(adapter);
        mLayoutManager.setUpAdapter(adapter);
    }

    public void setLayoutManager(ILayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        recyclerView.setLayoutManager(mLayoutManager.getLayoutManager());
    }

    public void setOnRefresh(OnRecyclerRefreshListener onRefresh) {
        this.mOnRecyclerRefreshListener = onRefresh;
    }

    @Override
    public void onRefresh() {
        mCurrentState = ACTION_PULL_TO_REFRESH;
        mOnRecyclerRefreshListener.onPullToRefresh();
    }

    public interface OnRecyclerRefreshListener {
        void onPullToRefresh();
        void onLoadMore();
    }

    public void onCompleteAction() {
        switch (mCurrentState) {
            case ACTION_PULL_TO_REFRESH:
                swipeRefreshLayout.setEnabled(true);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_LOAD_MORE:
                mAdapter.setLoadMore(false);
                swipeRefreshLayout.setEnabled(true);
                break;

        }
        mCurrentState = ACTION_IDLE;
    }
}
