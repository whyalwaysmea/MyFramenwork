package com.ithaha.myframework.fragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ithaha.myframework.R;
import com.ithaha.myframework.view.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/6/30.
 */
public class ListFragment extends BaseListFragment<String> implements IFragment{

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

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(getContext(), "List Fragment", Toast.LENGTH_SHORT).show();
        return false;
    }

    class MyViewHolder extends BaseViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.fragment_list_item);
        }

        @Override
        public void bindViewHolder(int position) {
            tv.setText(mData.get(position) + "");
        }
    }
}
