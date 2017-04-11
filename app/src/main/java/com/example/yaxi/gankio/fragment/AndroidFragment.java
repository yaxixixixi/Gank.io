package com.example.yaxi.gankio.fragment;

import android.app.Activity;
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
import android.widget.LinearLayout;

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
 * Android page.
 * Created by icursoft on 2017/4/11.
 */

public class AndroidFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener {

    private static final String TAG = AndroidFragment.class.getSimpleName();
    private static final int SINGLE_COUNT = 10;

    private RecyclerView mRecycler;

    private Context mContext;
    private boolean isLoadMore = false;
    private DataAdapter dataAdapter;

    private int page = 1;

    private List<Data.Results> mAllDataList = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_android, null, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_android);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach: ");
        super.onDetach();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.i(TAG, "onInflate: ");
    }



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
        Request.getDataWithCategory("Android", SINGLE_COUNT, page).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<Data.Results> results = response.body().getResults();
                Log.i(TAG, "onResponse: "+results.toString());
                if (results.size() == 0){
                    dataAdapter.loadMoreEnd();
                    return;
                }
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
