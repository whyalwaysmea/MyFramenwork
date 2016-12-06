package com.ithaha.myframework;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ithaha.myframework.base.BaseActivity;
import com.ithaha.myframework.fragment.SimpleFragment;

public class ViewPagerActivity extends BaseActivity {

    private ViewPager mViewpager;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_view_pager, R.string.viewpager_activity, MODE_BACK);
    }

    @Override
    protected void initView() {
        mViewpager = (ViewPager) findViewById(R.id.view_pager);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(myViewPagerAdapter);
//        mViewpager.setOffscreenPageLimit(2);

    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return SimpleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
