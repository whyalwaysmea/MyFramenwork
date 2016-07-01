package com.ithaha.myframework.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.myframework.R;
import com.ithaha.myframework.view.BaseViewHolder;

/**
 * Created by Long
 * on 2016/6/28.
 */
public abstract class BaseSectionListActivity extends BaseListActivity<String> {

    public static final int VIEW_TYPE_SECTION_VIEW  = 2;
    public static final int VIEW_TYPE_NORMAL_VIEW = 3;


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        if(VIEW_TYPE_SECTION_VIEW == viewType) {
            return onCreateSectionHeaderViewHolder(parent);
        }
        return onCreateSectionViewHolder(parent, viewType);
    }

    private BaseViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(this).inflate(R.layout.widget_section_header_view, parent, false);
        return new MyBaseSectionListViewHolder(view);
    }

    public abstract BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType);



    public class MyBaseSectionListViewHolder extends BaseViewHolder {

        TextView tv;

        public MyBaseSectionListViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_section_header_view);
        }

        @Override
        public void bindViewHolder(int position) {
            tv.setText("Header" + position);
        }
    }

}
