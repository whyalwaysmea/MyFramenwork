package com.ithaha.myframework.fragment;

import android.support.v4.app.Fragment;
import android.view.MenuItem;

/**
 * Created by Long
 * on 2016/7/1.
 */
public interface IFragment {
    public Fragment getFragment();
    public boolean onMenuItemClick(MenuItem item);
}
