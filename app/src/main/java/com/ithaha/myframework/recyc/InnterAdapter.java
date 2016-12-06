package com.ithaha.myframework.recyc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.myframework.R;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/12/6.
 */

public class InnterAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<String> datas = new ArrayList<>();

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textview = holder.getView(R.id.fragment_list_item);
        textview.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
