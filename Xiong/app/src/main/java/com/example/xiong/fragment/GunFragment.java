package com.example.xiong.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.adapter.GunAdapter;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.bean.GunBean;
import com.example.xiong.bean.ShouBean;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GunFragment extends BaseFragment<Presenter, Model> implements Contract.View {


    private ImageView iv;
    private PullToRefreshRecyclerView rv;
    private View v;

    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_gun;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresemter1.getDataFromModel("http://www.ipanda.com/kehuduan/video/index.json");
    }

    @Override
    protected void initView(View view) {
        rv =view.findViewById(R.id.rv);
        v = View.inflate(getActivity(), R.layout.gun,null);
        rv.addHeaderView(v);
    }

    @Override
    public void show(String ss) {
        iv = v.findViewById(R.id.iv);
        Gson gson = new Gson();
        Type type = new TypeToken<GunBean>() {
        }.getType();
        GunBean o = gson.fromJson(ss, type);
        List<GunBean.ListBean> list = o.getList();
        GunAdapter gunAdapter = new GunAdapter(getActivity(),R.layout.gunitem,list);
        rv.setAdapter(gunAdapter);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        Glide.with(getActivity()).load(list.get(0).getImage()).into(iv);

    }
}
