package com.ithaha.myframework.recyc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ithaha.myframework.R;

import java.util.ArrayList;

public class RecyclerActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler2);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));

        InnterAdapter innterAdapter = new InnterAdapter();
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(innterAdapter);
        rv.setAdapter(wrapper);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("" + i);
        }
        innterAdapter.setDatas(strings);

        View view = LayoutInflater.from(this).inflate(R.layout.header_view, null);
        wrapper.addHeaderView(view);
        View view2 = LayoutInflater.from(this).inflate(R.layout.pull_to_refresh_footer_view, null);
        wrapper.addHeaderView(view2);

        wrapper.addFootView(view);
        wrapper.addFootView(view);
        wrapper.addFootView(view);
        wrapper.notifyDataSetChanged();
    }
}
