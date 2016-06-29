package com.ithaha.myframework.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ithaha.myframework.MainActivity;
import com.ithaha.myframework.R;

import butterknife.ButterKnife;

/**
 * Created by HelloWorld on 2016/4/28.
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;
    protected final static String MODE_NORMAL = "MODE_NORMAL";
    protected final static String MODE_BACK = "MODE_BACK";
    protected final static String MODE_MENU = "MODE_MENU";
    protected final static String MODE_ALL = "MODE_ALL";

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if(MyApplication.sAppStatus == -1) {
            protectApp();
        } else {
            setUpContentView();
            initView();
            initData();
        }
    }

    protected void protectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("action", "force_kill");
        startActivity(intent);
    }


    protected abstract void setUpContentView();

    // 普通的setContentView
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    // 带有title的setContentView
    protected void setContentView(@LayoutRes int layoutResID, @StringRes int titleResID) {
        setContentView(layoutResID, titleResID, -1, MODE_NORMAL);
    }

    // 带有title和返回的setContentView
    protected void setContentView(@LayoutRes int layoutResID, int titleResID, String mode) {
        if(mode == MODE_BACK) {
            setContentView(layoutResID,titleResID,-1,MODE_BACK);
        }
    }

    // 带有title和menu的setContentView
    protected void setContentView(@LayoutRes int layoutResID, @StringRes int titleResID, int mendID) {
        setContentView(layoutResID, titleResID, mendID, MODE_MENU);
    }

    protected void setContentView(@LayoutRes int layoutResID, @StringRes int titleResID, int menuID, String mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResID,menuID,mode);
    }

    protected void setUpToolbar(@StringRes int titleResID, int menuID, String mode) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setUpToolbarTitle(titleResID);
        if(mode.equals(MODE_NORMAL)) {
        } else if(mode.equals(MODE_BACK)) {
            mToolbar.setNavigationIcon(R.drawable.icn_fanhui);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if(mode.equals(MODE_MENU)) {
            setUpMenu(menuID);
        } else if(mode.equals(MODE_ALL)) {
            mToolbar.setNavigationIcon(R.drawable.icn_fanhui);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setUpMenu(menuID);
        }
    }

    protected void setUpToolbarTitle(@StringRes int titleResID) {
        if(mToolbarTitle != null && titleResID > 0) {
            mToolbarTitle.setText(titleResID);
        }
    }

    protected void setUpMenu(int menuId) {
        if(mToolbar != null) {
            mToolbar.getMenu().clear();
            if(menuId > 0) {
                mToolbar.inflateMenu(menuId);
                mToolbar.setOnMenuItemClickListener(this);
            }
        }
    }


    protected void initView() {
        ButterKnife.bind(this);
    }

    protected void initData(){};

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
