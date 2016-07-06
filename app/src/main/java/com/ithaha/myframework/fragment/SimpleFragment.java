package com.ithaha.myframework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.myframework.R;

import java.util.Random;

/**
 * Created by Long
 * on 2016/7/4.
 */
public class SimpleFragment extends BaseFragment {

    private TextView mTv;
    private int mParameter;
    private int mPosition = -1;

    public static SimpleFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_list_item, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        mTv = (TextView) view.findViewById(R.id.item_tv);
    }

    @Override
    protected void initData() {
        if(mPosition == -1) {
            mParameter = new Random().nextInt(10);
            mPosition = getArguments().getInt("position");
        }
        mTv.setText("Page is:" + mPosition + " .. the num is" + mParameter);

    }

}
