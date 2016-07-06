package com.ithaha.myframework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
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
    private boolean isLazyLoadEnabled;

    protected  void initView(View view){}
    protected  void initData(){}

    public void enableLazyLoad(){
        isLazyLoadEnabled = true;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(toString() , ":onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(toString() , ":onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        Log.e(toString() , ":setUserVisibleHint:" + isVisibleToUser);
        checkIfLoadData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(toString() , ":onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(toString() , ":onViewCreated");
        if (!isLazyLoadEnabled){
            initView(view);
            initData();
        }else {
            if(!isViewInitialized) {
                initView(view);
                isViewInitialized = true;
            }
            if (savedInstanceState != null){
                onRestoreInstanceState(savedInstanceState);
            }
            if (isDataInitialized){
                initData();
            }else {
                checkIfLoadData();
            }
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataInitialized = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(toString() , ":onActivityCreated");

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e(toString() , ":onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInitialized = false;
        Log.e(toString() , ":onDestroyView");
    }

    private void checkIfLoadData() {
        if (isVisibleToUser && isViewInitialized && !isDataInitialized) {
            isDataInitialized = true;
//            TODO load data
            initData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(toString() , ":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(toString() , ":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(toString() , ":onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(toString() , ":onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(toString() , ":onStop");
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(toString() , ":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(toString() , ":onDetach");
    }


    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.e(toString() , ":onInflate");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(toString() , ":onHiddenChanged");
    }

}
