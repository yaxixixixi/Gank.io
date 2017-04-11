package com.example.yaxi.gankio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaxi.gankio.R;

/**扩展资源 page.
 * Created by icursoft on 2017/4/11.
 */

public class ExtendFragment extends BaseFragment {

    private View view;
    private RecyclerView extendRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_extend, null, false);
        extendRecycler = (RecyclerView) view.findViewById(R.id.recycler_extend);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected View getRootView() {
        return view;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return extendRecycler;
    }

    @Override
    protected String getPageType() {
        return "拓展资源";
    }
}
