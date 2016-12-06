package com.ithaha.myframework.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ithaha.myframework.R;

/**
 * Created by Long
 * on 2016/6/28.
 */
public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private boolean isLoadMore = false;
    private static final int VIEW_TYPE_LOAD_MORE = 1;
    private static final int VIEW_TYPE_NORMAL = 0;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_LOAD_MORE) {
            return onCreateLoadMoreFooterViewHolder(parent);
        }
        return onCreateNormalViewHolder(parent, viewType);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(isLoadMore && position == getItemCount() - 1) {
            if(holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                layoutParams.setFullSpan(true);
            }
        }
        holder.bindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return  getDataCount() + (isLoadMore ? 1 : 0);
    }


    @Override
    public int getItemViewType(int position) {
        if(isLoadMore && position == getItemCount() - 1) {
            return VIEW_TYPE_LOAD_MORE;
        }
        return getDataViewType(position);
    }

    // 默认返回正常布局
    public int getDataViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    public abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

    private BaseViewHolder onCreateLoadMoreFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_to_refresh_footer_view, parent, false);
        return new LoadMoreViewHolder(view);
    }

    protected abstract int getDataCount();

    class LoadMoreViewHolder extends BaseViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(int position) {

        }
    }

    public void setLoadMore(boolean load) {
        isLoadMore = load;
        if(load) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    public boolean isSectionHeader(int position) {
        return false;
    }

    public boolean isLoadMore(int position) {
        return isLoadMore && position == getItemCount() - 1;
    }


    // 针对GridLayoutManager
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        /*RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }*/
    }

    // 针对StaggeredGridLayoutManager
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }
}
