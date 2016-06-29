package com.ithaha.myframework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.myframework.base.BaseSectionListActivity;
import com.ithaha.myframework.view.BaseViewHolder;
import com.ithaha.myframework.view.ILayoutManager;
import com.ithaha.myframework.view.MyGridLayoutManager;

/**
 * Created by Long
 * on 2016/6/28.
 */
public class SectionListActivity extends BaseSectionListActivity {

    @Override
    protected void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add(i + "");
        }
    }

    @Override
    public ILayoutManager setLayoutManager() {
        return new MyGridLayoutManager(this, 3);
    }

    @Override
    public BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_list_item, parent, false);
        return new MyNormalViewHolder(view);
    }

    @Override
    public int getItemType(int position) {
        if(position % 10 == 0) {
            return VIEW_TYPE_SECTION_VIEW;
        }
        return VIEW_TYPE_NORMAL_VIEW;
    }

    @Override
    protected boolean setSectionHeader(int position) {
        if(position % 10 == 0) {
            return true;
        }
        return false;
    }

    class MyNormalViewHolder extends BaseViewHolder {

        TextView tv;

        public MyNormalViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }

        @Override
        public void bindViewHolder(int position) {
            tv.setText(""  + position);
        }
    }
}
