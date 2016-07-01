package com.ithaha.myframework;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.myframework.base.BaseListActivity;
import com.ithaha.myframework.view.BaseViewHolder;

public class ListActivity extends BaseListActivity<String> {



    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 30; i++) {
            mData.add("" + i);
        }
        mBaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPullToRefresh() {
        mHandler.sendEmptyMessageDelayed(0, 5000);
    }

    @Override
    public void onLoadMore() {
        mHandler.sendEmptyMessageDelayed(1, 5000);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0) {
                mData.clear();
                for (int i = 0; i < 20; i++) {
                    mData.add("onPullToRefresh" + i);
                }
                mBaseAdapter.notifyDataSetChanged();
                mPullToRefreshRecyclerView.onCompleteAction();
            } else if(msg.what == 1) {
                int size = mData.size();
                for (int i = size; i < size + 20; i++) {
                    mData.add("onLoadMore " + i);
                }
                mBaseAdapter.notifyDataSetChanged();
                mPullToRefreshRecyclerView.onCompleteAction();
            }
        }
    };

    class ViewHolder extends BaseViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }

        @Override
        public void bindViewHolder(int position) {
            tv.setText(mData.get(position));
        }
    }
}
