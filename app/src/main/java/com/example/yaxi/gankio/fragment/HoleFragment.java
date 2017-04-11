package com.example.yaxi.gankio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yaxi.gankio.R;

/**全部 page.
 * Created by icursoft on 2017/4/11.
 */

public class HoleFragment extends BaseFragment {

    private View view;
    private RecyclerView holeRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hole, null, false);
        holeRecycler = (RecyclerView) view.findViewById(R.id.recycler_hole);
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setProgressViewOffset(true, 50, 200);
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshLayout.setProgressBackgroundColor(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAllDataList.clear();
                page = 0;
                getDataWithCategory();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected View getRootView() {
        return view;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return holeRecycler;
    }

    @Override
    protected String getPageType() {
        return "all";
    }
}
