package com.example.yaxi.gankio.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yaxi.gankio.Data;
import com.example.yaxi.gankio.R;
import com.example.yaxi.gankio.activity.WebActivity;
import com.example.yaxi.gankio.adapter.DataAdapter;
import com.example.yaxi.gankio.http.Request;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by icursoft on 2017/4/11.
 */

public abstract class BaseFragment extends Fragment  implements BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener {

    private static final String TAG = BaseFragment.class.getSimpleName();
    protected static final int SINGLE_COUNT = 10;

    protected Context mContext;
    protected boolean isLoadMore = false;
    protected DataAdapter dataAdapter;

    protected int page = 1;

    protected List<Data.Results> mAllDataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        getDataWithCategory();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = getRootView();
        RecyclerView mRecycler = getRecyclerView();
        dataAdapter = new DataAdapter(R.layout.item_data);
        dataAdapter.setEnableLoadMore(true);
        dataAdapter.setOnLoadMoreListener(this,mRecycler);
        dataAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        dataAdapter.isFirstOnly(false);
        dataAdapter.setOnItemClickListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setAdapter(dataAdapter);
        return view;
    }

    protected abstract View getRootView();

    protected abstract RecyclerView getRecyclerView();

    protected abstract String getPageType();



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String url = mAllDataList.get(position).getUrl();
        startWebActivity(url);

    }

    @Override
    public void onLoadMoreRequested() {
        Log.i(TAG, "onLoadMoreRequested: ");
        getDataWithCategory();
    }

    private void updateData(List<Data.Results> results) {
        if (isLoadMore){
            dataAdapter.addData(results);
            dataAdapter.loadMoreComplete();
        }else{
            dataAdapter.setNewData(results);
        }
    }

    private void getDataWithCategory() {
        Request.getDataWithCategory(getPageType(), SINGLE_COUNT, page).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<Data.Results> results = response.body().getResults();
                Log.i(TAG, "onResponse: "+results.toString());
                mAllDataList.addAll(results);
                updateData(results);
                isLoadMore = true;
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
        page++;
    }

    private void startWebActivity(String url) {
        Log.i(TAG, "startWebActivity: url==>>" + url);
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
