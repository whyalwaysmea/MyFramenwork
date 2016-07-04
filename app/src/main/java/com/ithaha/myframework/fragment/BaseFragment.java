package com.ithaha.myframework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Long
 * on 2016/6/30.
 */
public class BaseFragment extends Fragment {


    private boolean isVisibleToUser;
    private boolean isViewInitialized;
    private boolean isDataInitialized;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(toString(),":onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(toString(),":onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(toString(),":setUserVisibleHint " + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        checkIfLoadData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(toString(),":onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(toString(),":onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        isViewInitialized = true;
        initView(view);
        if(isDataInitialized) {
            initData();
        } else {
            checkIfLoadData();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(toString(),":onSaveInstanceState");
    }

    protected void initView(View view) {

    }

    private void checkIfLoadData() {
        if(isViewInitialized && isVisibleToUser && !isDataInitialized) {
            isDataInitialized = true;
            initData();
        }
    }

    protected void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(toString(),":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(toString(),":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(toString(),":onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(toString(),":onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInitialized = false;
        Log.e(toString(),":onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(toString(),":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(toString(),":onDetach");
    }
}
