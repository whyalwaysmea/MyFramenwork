package com.ithaha.myframework;


import android.view.MenuItem;

import com.ithaha.myframework.base.BaseActivity;
import com.ithaha.myframework.bottom.TabLayout;
import com.ithaha.myframework.fragment.IFragment;
import com.ithaha.myframework.fragment.ListFragment;
import com.ithaha.myframework.fragment.SecondListFragment;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/6/30.
 */
public class TabActivity extends BaseActivity implements TabLayout.onItemClick {

    private IFragment mFragment;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_tab, R.string.tab_activity);
    }

    @Override
    protected void initView() {
        super.initView();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_tab_layout);
        ArrayList<TabLayout.Tab> tabs = new ArrayList<>();
        tabs.add(new TabLayout.Tab(R.drawable.selector_tab_contact, R.string.home_tab_audio, R.string.home_tab_audio_title, ListFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.selector_tab_moments, R.string.home_tab_news, R.string.home_tab_news_title, SecondListFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.selector_tab_msg, R.string.home_tab_read, R.string.home_tab_read_title, R.menu.menu_home,ListFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.selector_tab_profile, R.string.home_tab_profile, R.string.home_tab_profile_title, R.menu.menu_my,SecondListFragment.class));
        tabLayout.initData(tabs, this);
        tabLayout.setCurrentTab(0);

    }

    @Override
    public void onTabClick(TabLayout.Tab tab) {
        try {
            setUpToolbarTitle(tab.getTitleResId());
            setUpMenu(tab.getMenuResId());
            mFragment = tab.getTargetFragmentClz().newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.tab_activity_content, mFragment.getFragment()).commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mFragment.onMenuItemClick(item);
        return super.onMenuItemClick(item);
    }
}
