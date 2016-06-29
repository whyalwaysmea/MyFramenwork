package com.ithaha.myframework.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HelloWorld on 2016/6/15.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(int position);
}
