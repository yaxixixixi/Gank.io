package com.example.yaxi.gankio.adapter;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.yaxi.gankio.Data;
import com.example.yaxi.gankio.R;

import java.util.List;


/**
 * Created by icursoft on 2017/4/11.
 */

public class DataAdapter extends BaseQuickAdapter<Data.Results, BaseViewHolder> {

    public DataAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Data.Results item) {
        helper.setText(R.id.item_tv_title,item.getDesc())
            .setText(R.id.item_tv_name,item.getWho())
            .setText(R.id.item_tv_time,item.getPublishedAt());
    }

    @Override
    public void setLoadMoreView(LoadMoreView loadingView) {
        super.setLoadMoreView(loadingView);
    }

    @Override
    public void setNewData(List<Data.Results> data) {
        super.setNewData(data);
    }

    @Override
    public void addData(List<Data.Results> newData) {
        super.addData(newData);
    }

    @Override
    public void addData(Data.Results data) {
        super.addData(data);
    }

    @Override
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        super.setOnLoadMoreListener(requestLoadMoreListener, recyclerView);
    }

}
