package com.ithaha.myframework.bottom;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ithaha.myframework.R;
import com.ithaha.myframework.fragment.IFragment;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/6/30.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener {

    private View selectView;
    private int tabsCount;
    private onItemClick mOnItemClick;

    public TabLayout(Context context) {
        super(context);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
    }

    public void initData(ArrayList<Tab> tabs, onItemClick onItemClick) {
        if(tabs != null && tabs.size() > 0) {
            tabsCount = tabs.size();
            mOnItemClick = onItemClick;
            LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            for (int i = 0; i < tabs.size(); i++) {
                TabView tabView = new TabView(getContext());
                tabView.setTag(tabs.get(i));
                tabView.initData(tabs.get(i));
                tabView.setOnClickListener(this);
                addView(tabView, params);
            }
        }
    }

    public void setCurrentTab(int i) {
        if( i < tabsCount && i >= 0) {
            View view = getChildAt(i);
            onClick(view);
        }
    }

    @Override
    public void onClick(View v) {
        if(selectView != v) {
            v.setSelected(true);
            mOnItemClick.onTabClick((Tab) v.getTag());
            if(selectView != null) {
                selectView.setSelected(false);
            }
            selectView = v;
        }
    }

    public interface onItemClick {
        void onTabClick(Tab tab);
    }

    class TabView extends FrameLayout {

        private ImageView mTabImg;
        private TextView mTabLabel;
        private TextView mTabBadge;

        public TabView(Context context) {
            super(context);
            initView();
        }

        public TabView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        private void initView() {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation_item, this, true);
//            setOrientation(VERTICAL);
//            setGravity(Gravity.CENTER);
            mTabImg = (ImageView) findViewById(R.id.bottom_navigation_item_icon);
            mTabLabel = (TextView) findViewById(R.id.bottom_navigation_item_title);
            mTabBadge = (TextView) findViewById(R.id.bottom_navigation_notification);
        }

        public void initData(Tab tab) {
            mTabImg.setBackgroundResource(tab.imgResId);
            mTabLabel.setText(tab.labelResId);
            if(!TextUtils.isEmpty(tab.getBadge())) {
                mTabBadge.setVisibility(VISIBLE);
                mTabBadge.setText(tab.getBadge() + "");
            } else {
                mTabBadge.setVisibility(GONE);
            }
        }

    }

    public static class Tab {
        private int imgResId;
        private int labelResId;
        private int titleResId;
        private int menuResId;
        private String badge;
        private Fragment mFragment;
        private Class<? extends IFragment> targetFragmentClz;

        public Tab(int imgResId, int labelResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
        }

        public Tab(int imgResId, int labelResId, int titleResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
        }

        public Tab(int imgResId, int labelResId, int titleResId, int menuResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            this.menuResId = menuResId;
        }

        public Tab(int imgResId, int labelResId, int titleResId, Fragment fragment) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            mFragment = fragment;
        }

        public Tab(int imgResId, int labelResId, int titleResId, Class<? extends IFragment> targetFragmentClz) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            this.targetFragmentClz = targetFragmentClz;
        }

        public Tab(int imgResId, int labelResId, int titleResId, int menuResId, Class<? extends IFragment> targetFragmentClz) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            this.menuResId = menuResId;
            this.targetFragmentClz = targetFragmentClz;
        }

        public Tab(int imgResId, int labelResId, int titleResId, int menuResId, Fragment fragment) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            this.menuResId = menuResId;
            mFragment = fragment;
        }

        public Tab(int imgResId, int labelResId, int titleResId, String badge, Class<? extends IFragment> targetFragmentClz) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            this.badge = badge;
            this.targetFragmentClz = targetFragmentClz;
        }

        public Tab(int imgResId, int labelResId, int titleResId, String badge, Class<? extends IFragment> targetFragmentClz, int menuResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.titleResId = titleResId;
            this.badge = badge;
            this.targetFragmentClz = targetFragmentClz;
            this.menuResId = menuResId;
        }

        public int getImgResId() {
            return imgResId;
        }

        public int getLabelResId() {
            return labelResId;
        }

        public int getTitleResId() {
            return titleResId;
        }

        public int getMenuResId() {
            if(menuResId == 0) {
                return -1;
            }
            return menuResId;
        }

        public Fragment getFragment() {
            return mFragment;
        }

        public Class<? extends IFragment> getTargetFragmentClz() {
            return targetFragmentClz;
        }

        public String getBadge() {
            return badge;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }
    }
}
