package com.ithaha.myframework.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.myframework.R;
import com.ithaha.myframework.view.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/6/30.
 */
public class ListFragment extends BaseListFragment<String> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add("" + i);
        }
        mAdapter.notifyDataSetChanged();
    }

    class MyViewHolder extends BaseViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.fragment_list_item);
        }

        @Override
        public void bindViewHolder(int position) {
            System.out.println("hahaha");
            tv.setText(mData.get(position) + "");
        }
    }
}
