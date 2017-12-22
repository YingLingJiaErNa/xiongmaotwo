package com.example.xiong.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.xiong.R;
import com.example.xiong.adapter.HomeAdapter;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.bean.ShouBean;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhuFragment extends BaseFragment<Presenter, Model> implements Contract.View {

    private PullToRefreshRecyclerView pull;

    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_zhu;
    }

    @Override
    protected void initView(View view) {
        pull = (PullToRefreshRecyclerView) view.findViewById(R.id.pull);
        mPresemter1.getDataFromModel("http://www.ipanda.com/kehuduan/shouye/index.json");
    }

    @Override
    public void show(String ss) {
        Gson gson = new Gson();
        Type type = new TypeToken<ShouBean>() {
        }.getType();
        final ShouBean o = gson.fromJson(ss, type);
        final HomeAdapter homeAdapter = new HomeAdapter(getActivity(), o.getData());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pull.setAdapter(homeAdapter);
                pull.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            }
        });
    }

}
