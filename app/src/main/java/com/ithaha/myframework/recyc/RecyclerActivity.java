package com.ithaha.myframework.recyc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.ithaha.myframework.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(staggeredGridLayoutManager);

        final MyAdapter myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);


        /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return myAdapter.getItemViewType(position) == MyAdapter.TYPE_HEADER ? gridLayoutManager.getSpanCount() : 1;
            }
        });*/

        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }

        myAdapter.addDatas(data);

        myAdapter.setListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                View view = LayoutInflater.from(RecyclerActivity.this).inflate(R.layout.header_view, null);
                myAdapter.setHeaderView(view);
            }
        });

    }
}
