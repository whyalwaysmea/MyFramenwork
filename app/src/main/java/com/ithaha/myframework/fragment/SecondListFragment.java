package com.ithaha.myframework.fragment;

import android.os.Bundle;
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
public class SecondListFragment extends BaseListFragment<String> implements IFragment{

    public static SecondListFragment newInstance() {
        SecondListFragment fragment = new SecondListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLazyLoad();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    protected void initData() {
        if(mData == null) {
            mData = new ArrayList<>();
            for (int i = 50; i > 0; i--) {
                mData.add("" + i);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(getContext(), "Second List ", Toast.LENGTH_SHORT).show();
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
