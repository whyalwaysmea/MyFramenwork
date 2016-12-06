package com.ithaha.myframework;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ithaha.myframework.base.BaseActivity;
import com.ithaha.myframework.recyc.RecyclerActivity2;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView listActivity;
    private TextView stickyHeaderListActivity;
    private TextView sectionListActivity;
    private TextView tabActivity;
    private TextView viewPagerActivity;


    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main, R.string.main, R.menu.menu_home);
    }


    protected void initView() {
        listActivity = (TextView) findViewById(R.id.list_activity);
        listActivity.setOnClickListener(this);
        stickyHeaderListActivity = (TextView) findViewById(R.id.sticky_header_list_activity);
        stickyHeaderListActivity.setOnClickListener(this);
        sectionListActivity = (TextView) findViewById(R.id.section_list_activity);
        sectionListActivity.setOnClickListener(this);
        tabActivity = (TextView) findViewById(R.id.tab_activity);
        tabActivity.setOnClickListener(this);
        viewPagerActivity = (TextView) findViewById(R.id.viewpager_activity);
        viewPagerActivity.setOnClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        return super.onMenuItemClick(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getStringExtra("action");
        if("force_kill".equals(action)) {
            protectApp();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_activity:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.sticky_header_list_activity:
                startActivity(new Intent(this, StickyHeaderListActivity.class));
                break;
            case R.id.section_list_activity:
                startActivity(new Intent(this, SectionListActivity.class));
                break;
            case R.id.tab_activity:
                startActivity(new Intent(this, com.ithaha.myframework.TabActivity.class));
                break;
            case R.id.viewpager_activity:
//                startActivity(new Intent(this, ViewPagerActivity.class));
                startActivity(new Intent(this, RecyclerActivity2.class));

                break;
        }
    }

    protected void protectApp() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }


}
